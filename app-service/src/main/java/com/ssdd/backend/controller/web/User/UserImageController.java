package com.ssdd.backend.controller.web.User;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ssdd.backend.model.Image;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.UserService;

@Controller
public class UserImageController {

    @Autowired
    private UserService userService;

    @GetMapping("/usuario/{id}/imagen")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

        Optional<User> user = userService.findById(id);

        if (user.isPresent() && user.get().getImagenPerfil() != null) {
            Image imagen = user.get().getImagenPerfil();

            Resource file = new InputStreamResource(imagen.getImageFile().getBinaryStream());

            return ResponseEntity.ok()
                    .cacheControl(CacheControl.noCache().mustRevalidate())
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentLength(imagen.getImageFile().length())
                    .body(file);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/update_image")
    public String updateImage(
            @RequestParam String password,
            @RequestParam MultipartFile image,
            Principal principal,
            Model model) {

        try {
            String currentEmail = principal.getName();
            User user = userService.findByEmail(currentEmail)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            if (!userService.checkPassword(user, password)) {
                model.addAttribute("error", "La contraseña actual no es correcta.");
                return "userProfile"; 
            }

            userService.updateProfileImage(user.getId(), image);

            return "redirect:/userProfile.html";

        } catch (IOException | RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "userProfile";
        }
    }
}