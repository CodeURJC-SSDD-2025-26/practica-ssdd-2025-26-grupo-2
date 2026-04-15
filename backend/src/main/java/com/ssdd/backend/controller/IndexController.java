package com.ssdd.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssdd.backend.model.Travel;
import com.ssdd.backend.repository.ReviewRepository;
import com.ssdd.backend.service.TravelService; // <-- IMPORTANTE: Ahora sabe qué es TravelService

@Controller
public class IndexController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TravelService travelService; // <-- IMPORTANTE: Le inyectamos el servicio de viajes

    // Unimos "/" y "/index.html" en un solo método maestro
    @GetMapping({"/", "/index.html"})
    public String showIndex(Model model) {
        
        // 1. LÓGICA DE VIAJES
        List<Travel> todosLosViajes = travelService.getAllTravels();

        List<Travel> primeros6 = todosLosViajes.stream()
                .limit(6)
                .toList();

        String nombresAnimados = primeros6.stream()
                .map(Travel::getNombre)
                .collect(Collectors.joining(","));

        List<Travel> masPopulares = todosLosViajes.stream()
                .sorted((viaje1, viaje2) -> {
                    int reservas1 = (viaje1.getReservas() != null) ? viaje1.getReservas().size() : 0;
                    int reservas2 = (viaje2.getReservas() != null) ? viaje2.getReservas().size() : 0;
                    return Integer.compare(reservas2, reservas1);
                })
                .limit(4) 
                .toList();

        // 2. ENVIAMOS TODO AL HTML (Viajes y Reseñas a la vez)
        model.addAttribute("viajesNuevos", primeros6);
        model.addAttribute("viajesPopulares", masPopulares);
        model.addAttribute("nombresAnimados", nombresAnimados);
        model.addAttribute("reviews", reviewRepository.findAll()); 

        return "index";
    }
}