package com.ssdd.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssdd.backend.service.UserService;


@Controller
public class UserRegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(
            @RequestParam String name,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String conditions,
            Model model) {

        if (conditions == null) {
            model.addAttribute("errorRegister", "Debes aceptar los términos y condiciones.");
            return "signin";
        }

        try {
            userService.registerUser(name + " " + lastName, email, password);
            model.addAttribute("successRegister", "Usuario registrado correctamente.");
        } catch (RuntimeException e) {
            model.addAttribute("errorRegister", e.getMessage());
        }

        return "signin";
    }

    
}
