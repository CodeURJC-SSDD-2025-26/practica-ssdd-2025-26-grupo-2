package com.ssdd.backend.controller.web.Review;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssdd.backend.model.Review;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.ReviewService;
import com.ssdd.backend.service.TravelService;
import com.ssdd.backend.service.UserService;

@Controller
public class ReviewController {

    @Autowired
    private TravelService travelService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @GetMapping("/viajes/{id}")
    public String showTravelDetails(@PathVariable Long id, Model model, 
            @RequestParam(defaultValue = "0") int page) {
        
        Optional<Travel> viajeOpt = travelService.getTravelById(id);
        
        if (viajeOpt.isPresent()) {
            Travel viaje = viajeOpt.get();
            model.addAttribute("viaje", viaje);
            model.addAttribute("id", viaje.getId()); // Para el formulario de reserva

            // Paginación de reseñas (3 por página para que se note el efecto)
            Page<Review> reviewPage = reviewService.findByViaje(viaje, PageRequest.of(page, 3));
            
            model.addAttribute("reviews", reviewPage.getContent());
            
            // Variables para la lógica de Mustache
            model.addAttribute("hasReviewsPages", reviewPage.getTotalPages() > 1);
            model.addAttribute("hasPrevious", reviewPage.hasPrevious());
            model.addAttribute("hasNext", reviewPage.hasNext());
            model.addAttribute("prevPage", page - 1);
            model.addAttribute("nextPage", page + 1);
            model.addAttribute("displayPage", page + 1);
            model.addAttribute("totalPages", reviewPage.getTotalPages());
            
            return "travel_page_ext";
        }
        return "redirect:/viajes";
    }

    @PostMapping("/reviews/new")
    public String addReview(
            @RequestParam String comentario,
            @RequestParam int puntuacion,
            @RequestParam Long viajeId,
            Principal principal,
            RedirectAttributes redirectAttributes) {
        
        if (comentario == null || comentario.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorReview", "Debes escribir un comentario.");
            return "redirect:/viajes/" + viajeId;
        }

        if (principal != null) {
            User user = userService.findByEmail(principal.getName()).orElse(null);
            Travel viaje = travelService.getTravelById(viajeId).orElse(null);

            if (user != null && viaje != null) {
                Review review = new Review(puntuacion, comentario.trim(), user, viaje);
                reviewService.save(review);
            }
        }

        return "redirect:/viajes/" + viajeId;
    }

    @PostMapping("/reviews/delete")
    public String deleteReview(
            @RequestParam Long reviewId,
            @RequestParam Long viajeId,
            Principal principal) {

        if (principal != null) {
            Optional<Review> reviewOpt = reviewService.findById(reviewId);
            User user = userService.findByEmail(principal.getName()).orElse(null);

            if (reviewOpt.isPresent() && user != null) {
                Review review = reviewOpt.get();
                // Seguridad: Solo el autor puede borrar
                if (review.getAutor().getId().equals(user.getId())) {
                    reviewService.deleteById(reviewId);
                }
            }
        }
        return "redirect:/viajes/" + viajeId;
    }
}