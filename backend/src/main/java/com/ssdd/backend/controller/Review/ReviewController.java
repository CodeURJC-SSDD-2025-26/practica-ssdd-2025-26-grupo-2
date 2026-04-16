package com.ssdd.backend.controller.Review;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssdd.backend.model.Review;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.User;
import com.ssdd.backend.repository.ReviewRepository;
import com.ssdd.backend.repository.UserRepository;
import com.ssdd.backend.service.ReviewService;
import com.ssdd.backend.service.UserService;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private com.ssdd.backend.service.TravelService travelService;
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @PostMapping("/reviews/new")
    public String addReview(
        @RequestParam String comentario, 
        @RequestParam int puntuacion, 
        @RequestParam Long viajeId, 
        Principal principal) {
        
        if (principal != null) {
            User user = userService.findByEmail(principal.getName()).get();
            Travel viaje = travelService.getTravelById(viajeId).get();
            
            Review review = new Review(puntuacion, comentario, user, viaje);
            reviewService.save(review);
        }
        return "redirect:/viajes/" + viajeId;
    }
}