package com.ssdd.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssdd.backend.model.Reservation;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.ReservationService;
import com.ssdd.backend.service.UserService;

@Controller
public class UserManagementController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/userManagement.html")
    public String displayUsers(Model model) {
        List<User> listaUsuarios = userService.findAll();

        model.addAttribute("usuarios", listaUsuarios);

        return "userManagement";
    }

    @PostMapping("/admin/usuarios/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.ok("Usuario eliminado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar");
        }
    }

    @PostMapping("/admin/usuarios/reset-imagen/{id}")
    public ResponseEntity<String> resetearImagen(@PathVariable Long id) {
        try {
            User user = userService.findById(id).orElseThrow();
            user.setImagenPerfil(null);
            userService.save(user);
            return ResponseEntity.ok("Imagen reseteada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al resetear imagen");
        }
    }

    @GetMapping("/admin/usuarios/{id}/reservas")
    public String reservasUsuario(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElseThrow();
        List<Reservation> reservas = reservationService.findByUsuario(user);
        model.addAttribute("user", user);
        model.addAttribute("reservas", reservas);
        return "userReservas";
    }

}
