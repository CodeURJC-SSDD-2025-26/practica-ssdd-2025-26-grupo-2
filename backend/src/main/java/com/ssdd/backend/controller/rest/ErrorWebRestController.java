package com.ssdd.backend.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/errors")
public class ErrorWebRestController {

    @GetMapping("/404")
    public ResponseEntity<String> show404Page() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404 - Pagina no encontrada");
    }

    @GetMapping("/403")
    public ResponseEntity<String> show403Page() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error 403 - Acceso denegado");
    }

    @GetMapping("/loginFailure")
    public ResponseEntity<String> showLoginFailurePage() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticacion - Credenciales incorrectas");
    }
}
