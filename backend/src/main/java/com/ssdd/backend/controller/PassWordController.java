package com.ssdd.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssdd.backend.model.User;
import com.ssdd.backend.service.UserService;

@Controller
public class PassWordController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgotPass";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(
            @RequestParam String email,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model) {

        Optional<User> userOpt = userService.findByEmail(email);

        if (userOpt.isEmpty()) {
            model.addAttribute("error", "No existe ningún usuario con ese correo.");
            return "forgotPass";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden.");
            return "forgotPass";
        }

        if (newPassword.length() < 4) {
            model.addAttribute("error", "La contraseña debe tener al menos 4 caracteres.");
            return "forgotPass";
        }

        User user = userOpt.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userService.save(user);

        return "redirect:/signin";
    }
}
