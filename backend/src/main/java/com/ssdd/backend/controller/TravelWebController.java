package com.ssdd.backend.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssdd.backend.model.Image;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.repository.ReviewRepository;
import com.ssdd.backend.service.ImageService;
import com.ssdd.backend.service.TravelService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TravelWebController {

    @Autowired
    private TravelService travelService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ReviewRepository reviewRepository;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
        }

        var csrfToken = (org.springframework.security.web.csrf.CsrfToken) request
                .getAttribute(org.springframework.security.web.csrf.CsrfToken.class.getName());
        if (csrfToken != null) {
            model.addAttribute("token", csrfToken.getToken());
        }
    }

    // 1. Mostrar TODOS los viajes
    @GetMapping("/viajes")
    public String showTravels(Model model) {
        model.addAttribute("viajes", travelService.getAllTravels());
        return "travel_page";
    }

    // 2. Mostrar UN viaje en detalle
    @GetMapping("/viajes/{id}")
    public String showTravel(Model model, @PathVariable("id") Long id) {
        Optional<Travel> viaje = travelService.getTravelById(id);
        if (viaje.isPresent()) {
            model.addAttribute("viaje", viaje.get());
            // TRAEMOS SOLO LAS RESEÑAS DE ESTE VIAJE
            model.addAttribute("reviews", reviewRepository.findByViajeId(id));
            return "travel_page_ext";
        }
        return "redirect:/";
    }

    // 3. Borrar un viaje
    @PostMapping("/borrarviaje/{id}")
    public String removeTravel(Model model, @PathVariable("id") Long id) {
        Optional<Travel> viaje = travelService.getTravelById(id);
        if (viaje.isPresent()) {
            travelService.delete(id);
        }
        return "redirect:/journeyManagement";
    }

    // 4. Mostrar el formulario para crear un viaje nuevo
    @GetMapping("/nuevoviaje")
    public String newTravel(Model model) {
        return "addJourney"; // El HTML con el formulario vacío
    }

    // 5. Procesar la creación de un viaje nuevo
    @PostMapping("/nuevoviaje")
    public String newTravelProcess(Travel viaje,
            @RequestParam(value = "imagenOculta", required = false) String imagenOculta)
            throws IOException {

        if (imagenOculta != null && !imagenOculta.isEmpty()) {
            String base64Data = imagenOculta.split(",")[1];
            byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
            InputStream inputStream = new ByteArrayInputStream(decodedBytes);
            Image image = imageService.createImage(inputStream);
            viaje.setImagen(image);
        }

        travelService.save(viaje);
        return "redirect:/journeyManagement";
    }

    // ---------------------------------------------------------
    // --- NUEVAS RUTAS "LIMPIAS" PARA MODIFICAR EL VIAJE ------
    // ---------------------------------------------------------

    // 6. Mostrar el formulario para editar un viaje que ya existe
    @GetMapping("/modificarviaje/{id}")
    public String editTravel(Model model, @PathVariable long id) { 
        
        // --- NUESTRO CHIVATO ---
        System.out.println(">>>>>>>>>> ¡BINGO! JAVA HA RECIBIDO EL CLIC PARA EL VIAJE: " + id);

        Optional<Travel> viaje = travelService.getTravelById(id);

        if (viaje.isPresent()) {
            model.addAttribute("viaje", viaje.get());
            return "editJourney"; 
        } else {
            return "redirect:/journeyManagement";
        }
    }

    // 7. Recibir y guardar los cambios del viaje editado
    // 7. Recibir y guardar los cambios del viaje editado
    @PostMapping("/modificarviaje")
    public String editTravelProcess(Travel viaje,
            @RequestParam(value = "imagenOculta", required = false) String imagenOculta,
            @RequestParam(value = "removeImage", required = false, defaultValue = "false") boolean removeImage)
            throws IOException, SQLException {

        Travel dbTravel = travelService.getTravelById(viaje.getId()).orElseThrow();

        if (imagenOculta != null && !imagenOculta.isEmpty()) {
            String base64Data = imagenOculta.split(",")[1];
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Data);
            InputStream inputStream = new ByteArrayInputStream(decodedBytes);

            if (dbTravel.getImagen() == null) {
                Image image = imageService.createImage(inputStream);
                viaje.setImagen(image);
            } else {
                Image image = imageService.replaceImageFile(dbTravel.getImagen().getId(), inputStream);
                viaje.setImagen(image);
            }
        } else if (removeImage) {
            if (dbTravel.getImagen() != null) {
                imageService.deleteImage(dbTravel.getImagen().getId());
                viaje.setImagen(null);
            }
        } else {
            viaje.setImagen(dbTravel.getImagen());
        }

        travelService.save(viaje);
        return "redirect:/journeyManagement";
    }
    // ---------------------------------------------------------

    // Mostrar la tabla de gestión de viajes
    @GetMapping("/journeyManagement")
    public String showManagementTable(Model model) {
        model.addAttribute("viajes", travelService.getAllTravels());
        return "journeyManagement";
    }

    // Mostrar el menú principal de administración
    @GetMapping("/admin")
    public String showAdminMenu() {
        return "admin";
    }

    // Este método devuelve la foto real del viaje
    @GetMapping("/viajes/{id}/imagen")
    public ResponseEntity<Object> downloadImage(@PathVariable("id") Long id) throws SQLException {
        Optional<Travel> viaje = travelService.getTravelById(id);

        if (viaje.isPresent() && viaje.get().getImagen() != null) {
            Resource file = new InputStreamResource(viaje.get().getImagen().getImageFile().getBinaryStream());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(viaje.get().getImagen().getImageFile().length())
                    .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   
}