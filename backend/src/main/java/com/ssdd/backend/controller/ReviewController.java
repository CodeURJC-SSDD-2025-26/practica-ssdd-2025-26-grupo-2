package com.ssdd.backend.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssdd.backend.model.Review;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.User;
import com.ssdd.backend.repository.ReviewRepository;
import com.ssdd.backend.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private com.ssdd.backend.service.TravelService travelService;

    @PostMapping("/reviews/new")
    // 1. Añadimos el parámetro Long viajeId que vendrá del formulario
    public String addReview(String comentario, int puntuacion, Long viajeId, HttpServletRequest request) {
    Principal principal = request.getUserPrincipal();
    if (principal != null) {
        Optional<User> user = userRepository.findByEmail(principal.getName());
        Optional<Travel> viaje = travelService.getTravelById(viajeId); // Buscamos el viaje

        if (user.isPresent() && viaje.isPresent()) {
            // Guardamos la reseña asociada al autor Y al viaje
            Review review = new Review(puntuacion, comentario, user.get(), viaje.get());
            reviewRepository.save(review);
        }
    }
    return "redirect:/viajes/" + viajeId;
}
}