package com.ssdd.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssdd.backend.model.Travel;
import com.ssdd.backend.repository.ReviewRepository;
import com.ssdd.backend.service.TravelService; 

@Controller
public class IndexController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TravelService travelService; 

    @GetMapping({"/", "/index.html"})
    public String showIndex(Model model) {
        
        List<Travel> todosLosViajes = travelService.getAllTravels();

        // 1. VIAJES PARA EL CARRUSEL (Simplemente cortamos 6)
        List<Travel> paraCarrusel = todosLosViajes.stream()
                .limit(6) 
                .toList();

        // 2. VIAJES PARA LAS OFERTAS Y FONDOS (Simplemente cortamos 4, pero ignorando Putrajaya)
        List<Travel> paraOfertas = todosLosViajes.stream()
                .filter(viaje -> !viaje.getNombre().equalsIgnoreCase("Putrajaya")) 
                .limit(4) 
                .toList();

        // 3. NOMBRES ANIMADOS (Usamos la barra mágica "|")
        String nombresAnimados = paraOfertas.stream()
                .map(Travel::getNombre)
                .collect(Collectors.joining("|"));

        
        model.addAttribute("viajesPopulares", paraCarrusel); 
        model.addAttribute("viajesNuevos", paraOfertas);     
        model.addAttribute("nombresAnimados", nombresAnimados); // <-- ¡El salvavidas!
        model.addAttribute("reviews", reviewRepository.findAll()); 

        return "index";
    }
}