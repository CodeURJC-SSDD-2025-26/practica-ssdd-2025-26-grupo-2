package com.ssdd.backend.controller.rest;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable; // <-- 1. IMPORTANTE: Añadir este import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.TravelService;
import com.ssdd.backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/global")
public class GlobalRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private TravelService travelService;

    @GetMapping({"/"})
    public ResponseEntity<Map<String, Object>> getGlobalAttributes(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        Map<String, Object> response = new LinkedHashMap<>();
        
        
        response.put("viajes", travelService.getAllTravels(Pageable.unpaged())
                .getContent() 
                .stream()
                .map(this::toTravelResponse)
                .toList());

        if (principal != null) {
            response.put("logged", true);
            response.put("userName", principal.getName());
            response.put("admin", request.isUserInRole("ADMIN") || request.isUserInRole("ROLE_ADMIN"));

            Optional<User> user = userService.findByEmail(principal.getName());
            user.ifPresent(value -> response.put("user", toUserResponse(value)));
        } else {
            response.put("logged", false);
        }

        return ResponseEntity.ok(response);
    }

    private Map<String, Object> toTravelResponse(Travel travel) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", travel.getId());
        response.put("nombre", travel.getNombre());
        response.put("descripcion", travel.getDescripcion());
        response.put("pais", travel.getPais());
        response.put("precio", travel.getPrecio());
        response.put("transporte", travel.getTransporte());
        response.put("alojamiento", travel.getAlojamiento());
        response.put("fechaInicio", travel.getFechaInicio());
        response.put("fechaFin", travel.getFechaFin());
        response.put("maxPlazas", travel.getMaxPlazas());
        response.put("numNoches", travel.getNumNoches());
        response.put("tieneImagen", travel.getTieneImagen());
        return response;
    }

    private Map<String, Object> toUserResponse(User user) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", user.getId());
        response.put("nombre", user.getNombre());
        response.put("email", user.getEmail());
        response.put("roles", user.getRoles());
        response.put("tieneImagenPerfil", user.getImagenPerfil() != null);
        return response;
    }
}