package com.ssdd.backend.controller;

import org.springframework.beans.factory.annotation.Autowired; // Asegúrate de importar tu repositorio
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssdd.backend.repository.ReviewRepository;

@Controller
public class IndexController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public String indexReturn(Model model) {
        // Añadimos las reseñas para la ruta principal
        model.addAttribute("reviews", reviewRepository.findAll());
    	return "index";
    }

    @GetMapping("/index.html")
    public String index(Model model) {
        // Añadimos las reseñas también aquí por si entran vía /index.html
        model.addAttribute("reviews", reviewRepository.findAll());
        return "index";
    }
}