package com.ssdd.backend.controller;

import com.ssdd.backend.model.User;
import com.ssdd.backend.service.UserService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UserService userService; 

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ROLE_ADMIN"));


            Optional<User> user = userService.findByEmail(principal.getName());
            //para poder hacer {{user.imagenPerfil}}
            user.ifPresent(u -> model.addAttribute("user", u));
            // ----------------------------------
            
        } else {
            model.addAttribute("logged", false);
        }
    }
}