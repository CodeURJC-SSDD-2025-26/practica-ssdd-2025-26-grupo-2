package com.ssdd.backend.controller.Review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssdd.backend.model.Review;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.ReviewService;
import com.ssdd.backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin/usuarios/{id}/reseñas")
    public String verReseñasUsuario(Model model, @PathVariable long id, HttpServletRequest request) {
        User targetUser = userService.findById(id).orElseThrow();
        List<Review> userReviews = reviewService.findByAutor(targetUser);

        model.addAttribute("targetUser", targetUser);
        model.addAttribute("userReviews", userReviews);
        
        var csrf = (org.springframework.security.web.csrf.CsrfToken) request.getAttribute("_csrf");
        if (csrf != null) {
            model.addAttribute("tokenSeguridad", csrf.getToken());
        }

        return "admin_user_reviews";
    }

    @PostMapping("/admin/reviews/delete")
    @ResponseBody
    public ResponseEntity<Void> deleteReview(@RequestParam long reviewId) {
        try {
            reviewService.deleteById(reviewId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}