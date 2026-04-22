package com.ssdd.backend.controller.Review;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssdd.backend.model.Review;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.ReviewService;
import com.ssdd.backend.service.UserService;
import com.ssdd.backend.service.TravelService;

@Controller
public class ReviewController {

    @Autowired
    private TravelService travelService;
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

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
                if (review.getAutor().getId().equals(user.getId())) {
                    reviewService.deleteById(reviewId);
                }
            }
        }
        return "redirect:/viajes/" + viajeId;
    }
}