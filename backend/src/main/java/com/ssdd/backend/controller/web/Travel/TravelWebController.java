package com.ssdd.backend.controller.Travel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
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
import com.ssdd.backend.model.Review;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.repository.ReviewRepository;
import com.ssdd.backend.service.ImageService;
import com.ssdd.backend.service.TravelService;

import com.ssdd.backend.model.CreditCard;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.CreditCardService;
import com.ssdd.backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TravelWebController {

    @Autowired
    private UserService userService;

    @Autowired
    private CreditCardService creditCardService;

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

    
    @GetMapping("/viajes")
    public String showTravels(Model model) {
        model.addAttribute("viajes", travelService.getAllTravels());
        return "travel_page";
    }


    @GetMapping("/viajes/{id}")
    public String showTravel(Model model, @PathVariable("id") Long id, Principal principal) {
        Optional<Travel> viaje = travelService.getTravelById(id);

        if (viaje.isPresent()) {
            model.addAttribute("viaje", viaje.get());
            model.addAttribute("reviews", reviewRepository.findByViajeId(id));
            model.addAttribute("tieneImagen", viaje.get().getImagen() != null);

            if (principal != null) {
                Optional<User> userOpt = userService.findByEmail(principal.getName());
                List<Review> reviews = reviewRepository.findByViajeId(id);

                if (userOpt.isPresent()) {
                    User user = userOpt.get();

                    model.addAttribute("logged", true);
                    model.addAttribute("userName", user.getNombre());
                    for (Review r : reviews) {
                        if (r.getAutor().getId().equals(user.getId())) {
                            r.setIsOwner(true);
                        }
                    }

                    Optional<CreditCard> creditCardOpt = creditCardService.findByUser(user);

                    if (creditCardOpt.isPresent()) {
                        CreditCard savedCard = creditCardOpt.get();
                        model.addAttribute("hasSavedCard", true);
                        model.addAttribute("savedCard", savedCard);
                        model.addAttribute("ultimos4", savedCard.getUltimosCuatro());
                    } else {
                        model.addAttribute("hasSavedCard", false);
                    }
                } else {
                    model.addAttribute("hasSavedCard", false);
                }
                model.addAttribute("reviews", reviews);
            } else {
                model.addAttribute("hasSavedCard", false);
            }
            

            return "travel_page_ext";
        }

        return "redirect:/";
    }


    @PostMapping("/borrarviaje/{id}")
    public String removeTravel(Model model, @PathVariable("id") Long id) {
        Optional<Travel> viaje = travelService.getTravelById(id);
        if (viaje.isPresent()) {
            travelService.delete(id);
        }
        return "redirect:/journeyManagement";
    }

    
    @GetMapping("/nuevoviaje")
    public String newTravel(Model model) {
        return "addJourney"; 
    }

    
   @PostMapping("/nuevoviaje")
    public String newTravelProcess(Travel viaje,
            @RequestParam(value = "imagenOculta", required = false) String imagenOculta,
            Model model) throws IOException {

        if (imagenOculta == null || imagenOculta.isEmpty()) {
           
            model.addAttribute("error", "Es obligatorio subir una imagen para crear el viaje.");
            
            return "addJourney"; 
        }

    
        String base64Data = imagenOculta.split(",")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
        InputStream inputStream = new ByteArrayInputStream(decodedBytes);
        Image image = imageService.createImage(inputStream);
        viaje.setImagen(image);

        travelService.save(viaje);
        return "redirect:/journeyManagement";
    }

    @GetMapping("/modificarviaje/{id}")
    public String editTravel(Model model, @PathVariable long id) {

        Optional<Travel> viaje = travelService.getTravelById(id);

        if (viaje.isPresent()) {
            model.addAttribute("viaje", viaje.get());
            return "editJourney";
        } else {
            return "redirect:/journeyManagement";
        }
    }

    
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
    
    @GetMapping("/journeyManagement")
    public String showManagementTable(Model model) {
        model.addAttribute("viajes", travelService.getAllTravels());
        return "journeyManagement";
    }

    
    @GetMapping("/admin")
    public String showAdminMenu() {
        return "admin";
    }

    
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