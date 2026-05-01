package com.ssdd.backend.controller.rest;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ssdd.backend.service.EmailService;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactRestController {

    @Autowired
    private EmailService emailService;

    @GetMapping({ "", "/" })
    public ResponseEntity<Map<String, Object>> getContact() {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("titulo", "Contactanos");
        response.put("direccion", "Calle Tulipan, s/n, 28933 Mostoles, Madrid");
        response.put("telefono", "+34 123 456 789");
        response.put("email", "byebye.application@gmail.com");

        return ResponseEntity.ok(response);
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Map<String, Object>> sendContact(@RequestBody Map<String, String> contactRequest) {

        String nombre = getRequiredField(contactRequest, "nombre");
        String apellidos = getRequiredField(contactRequest, "apellidos");
        String email = getRequiredField(contactRequest, "email");
        String mensaje = getRequiredField(contactRequest, "mensaje");

        emailService.sendContactEmail(nombre, apellidos, email, mensaje);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("enviado", true);
        response.put("mensaje", "Mensaje enviado correctamente");

        URI location = fromCurrentRequest()
                .build()
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    private String getRequiredField(Map<String, String> contactRequest, String fieldName) {

        if (contactRequest == null || !StringUtils.hasText(contactRequest.get(fieldName))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The field '" + fieldName + "' is required");
        }

        return contactRequest.get(fieldName).trim();
    }
}
