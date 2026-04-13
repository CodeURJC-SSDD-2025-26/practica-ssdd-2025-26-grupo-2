package com.ssdd.backend.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
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
import org.springframework.web.multipart.MultipartFile;

import com.ssdd.backend.model.Image;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.repository.ReviewRepository;
import com.ssdd.backend.service.ImageService;
import com.ssdd.backend.service.TravelService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
    }

    // 1. Mostrar TODOS los viajes 
    @GetMapping("/viajes") 
    public String showTravels(Model model) {
        model.addAttribute("viajes", travelService.getAllTravels());
        return "travel_page"; 
    }

    // 2. Mostrar UN viaje en detalle
    // 2. Mostrar UN viaje en detalle
    @GetMapping("/viajes/{id}")
    public String showTravel(Model model, @PathVariable long id) {
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
    public String removeTravel(Model model, @PathVariable long id) {
        Optional<Travel> viaje = travelService.getTravelById(id);
        if (viaje.isPresent()) {
            // Si la entidad tiene imagen, quizás quieras borrarla del ImageService primero
            travelService.delete(id); 
        }
        return "redirect:/journeyManagement"; 
    }

    // 4. Mostrar el formulario para crear un viaje nuevo
    @GetMapping("/nuevoviaje")
    public String newTravel(Model model) {
        return "addJourney"; // El HTML con el formulario vacío
    }



// ... (tus otros imports)

@PostMapping("/nuevoviaje")
public String newTravelProcess(Travel viaje, @org.springframework.web.bind.annotation.RequestParam(value = "imagenOculta", required = false) String imagenOculta) throws IOException {
    
    if (imagenOculta != null && !imagenOculta.isEmpty()) {
        // 1. Extraemos solo la parte del código (quitando el encabezado "data:image...")
        String base64Data = imagenOculta.split(",")[1];
        
        // 2. Decodificamos el texto a bytes
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
        
        // 3. Lo convertimos en un chorro de datos (InputStream)
        InputStream inputStream = new ByteArrayInputStream(decodedBytes);
        
        // 4. ¡Aquí usamos TU ImageService! 
        // Tu método createImage probablemente ya devuelve un objeto de tipo Image
        Image image = imageService.createImage(inputStream);
        
        // 5. Y lo asociamos a tu entidad Travel
        viaje.setImagen(image);
    }

    travelService.save(viaje);
    return "redirect:/journeyManagement";
}

    // 6. Mostrar el formulario para editar un viaje que ya existe
    @GetMapping("/editarviaje/{id}")
    public String editTravel(Model model, @PathVariable long id) {
        Optional<Travel> viaje = travelService.getTravelById(id);
        if (viaje.isPresent()) {
            model.addAttribute("viaje", viaje.get());
            return "editTravelPage"; // El HTML con el formulario lleno con los datos actuales
        } else {
            return "redirect:/";
        }
    }

    // 7. Recibir y guardar los cambios del viaje editado
    @PostMapping("/editarviaje")
    public String editTravelProcess(Model model, Travel viaje, boolean removeImage, MultipartFile imageField)
            throws IOException, SQLException {

        updateImage(viaje, removeImage, imageField);

        travelService.save(viaje);

        return "redirect:/viajes/" + viaje.getId();
    }

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

    // Método auxiliar para gestionar la lógica de cambiar/borrar la foto
    private void updateImage(Travel viaje, boolean removeImage, MultipartFile imageField)
            throws IOException, SQLException {

        if (!imageField.isEmpty()) {
            Travel dbTravel = travelService.getTravelById(viaje.getId()).orElseThrow();

            if (dbTravel.getImagen() == null) {
                Image image = imageService.createImage(imageField.getInputStream());
                viaje.setImagen(image);
            } else {
                Image image = imageService.replaceImageFile(dbTravel.getImagen().getId(), imageField.getInputStream());
                viaje.setImagen(image);
            }
        } else {
            if (removeImage) {
                if (viaje.getImagen() != null) {
                    imageService.deleteImage(viaje.getImagen().getId());
                    viaje.setImagen(null);
                }
            } else {
                // Mantener la imagen actual si no han subido ninguna nueva ni le han dado a borrar
                Travel dbTravel = travelService.getTravelById(viaje.getId()).orElseThrow();
                viaje.setImagen(dbTravel.getImagen());
            }
        }
    }

    // Este método devuelve la foto real del viaje
    @GetMapping("/viajes/{id}/imagen")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        Optional<Travel> viaje = travelService.getTravelById(id);

        // Comprobamos si el viaje existe y si tiene una imagen guardada
        if (viaje.isPresent() && viaje.get().getImagen() != null) {
            
            // Extraemos la foto de la base de datos 
            Resource file = new InputStreamResource(viaje.get().getImagen().getImageFile().getBinaryStream());
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg") 
                    .contentLength(viaje.get().getImagen().getImageFile().length())
                    .body(file);
        } else {
            // Si el viaje no tiene foto, devolvemos un Error 404
            return ResponseEntity.notFound().build();
        }
    }
}