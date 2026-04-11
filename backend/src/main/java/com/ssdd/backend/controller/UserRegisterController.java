package com.ssdd.backend.controller;

import com.ssdd.backend.repository.UserRepository;
import com.ssdd.backend.service.ImageService;
import com.ssdd.backend.service.UserService;
import com.ssdd.backend.model.User;

import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

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
            model.addAttribute("error", e.getMessage());
        }

        return "signin";
    }
}
