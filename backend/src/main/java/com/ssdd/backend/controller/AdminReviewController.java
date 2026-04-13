package com.ssdd.backend.controller;

import com.ssdd.backend.model.Review;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.ReviewService;
import com.ssdd.backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminReviewController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    // 1. Muestra la página de gestión de reseñas de un usuario específico
    @GetMapping("/admin/usuario/{id}/reseñas")
    public String showUserReviews(Model model, @PathVariable long id, HttpServletRequest request) {
        User targetUser = userService.findById(id).orElseThrow();
        List<Review> userReviews = reviewService.findByAutor(targetUser);

        // Capturamos el token de seguridad
        org.springframework.security.web.csrf.CsrfToken csrf = 
            (org.springframework.security.web.csrf.CsrfToken) request.getAttribute("_csrf");
        
        model.addAttribute("targetUser", targetUser);
        model.addAttribute("userReviews", userReviews);
        model.addAttribute("admin", true);
        
        // IMPORTANTE: Este nombre debe coincidir con el del HTML
        if (csrf != null) {
            model.addAttribute("tokenSeguridad", csrf.getToken());
        }

        return "admin_user_reviews";
    }

@PostMapping("/admin/reviews/delete")
    public org.springframework.http.ResponseEntity<Void> deleteReview(@RequestParam long reviewId) {
        try {
            reviewService.deleteById(reviewId);
            // Devolvemos un 200 OK para que el JavaScript sepa que ha funcionado
            return org.springframework.http.ResponseEntity.ok().build();
        } catch (Exception e) {
            return org.springframework.http.ResponseEntity.status(400).build();
        }
    }
}