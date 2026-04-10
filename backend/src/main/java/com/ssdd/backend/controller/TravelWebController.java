package com.ssdd.backend.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.Image;
import com.ssdd.backend.service.TravelService;
import com.ssdd.backend.service.ImageService;

@Controller
public class TravelWebController {

    @Autowired
    private TravelService travelService;

    @Autowired
    private ImageService imageService;

    // Este método se ejecuta SIEMPRE antes de cargar cualquier página.
    // Es ideal para saber si hay que mostrar el botón de "Login" o el nombre del usuario.
    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
        } else {
            model.addAttribute("logged", false);
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
    public String showTravel(Model model, @PathVariable long id) {
        Optional<Travel> viaje = travelService.getTravelById(id);
        if (viaje.isPresent()) {
            model.addAttribute("viaje", viaje.get());
            return "travel_page_ext"; 
        } else {
            return "redirect:/"; 
        }
    }

    // 3. Borrar un viaje
    @PostMapping("/borrarviaje/{id}")
    public String removeTravel(Model model, @PathVariable long id) {
        Optional<Travel> viaje = travelService.getTravelById(id);
        if (viaje.isPresent()) {
            // Si la entidad tiene imagen, quizás quieras borrarla del ImageService primero
            travelService.delete(id); 
        }
        return "redirect:/"; 
    }

    // 4. Mostrar el formulario para crear un viaje nuevo
    @GetMapping("/nuevoviaje")
    public String newTravel(Model model) {
        return "addJourney"; // El HTML con el formulario vacío
    }

    // 5. Recibir y guardar los datos del nuevo viaje
    @PostMapping("/nuevoviaje")
    public String newTravelProcess(Model model, Travel viaje, MultipartFile imageField) throws IOException {
        
        // Si han subido una foto, la guardamos y se la asignamos al viaje
        if (!imageField.isEmpty()) {
            Image image = imageService.createImage(imageField.getInputStream());
            viaje.setImagen(image);
        }

        travelService.save(viaje);

        // Redirigimos a la página de detalle del viaje recién creado
        return "redirect:/viajes/" + viaje.getId();
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
            
            // Extraemos la foto de la base de datos (Usando la lógica de tu profe)
            Resource file = new InputStreamResource(viaje.get().getImagen().getImageFile().getBinaryStream());
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg") // Le decimos al navegador que es una foto
                    .contentLength(viaje.get().getImagen().getImageFile().length())
                    .body(file);
        } else {
            // Si el viaje no tiene foto, devolvemos un Error 404
            return ResponseEntity.notFound().build();
        }
    }
}