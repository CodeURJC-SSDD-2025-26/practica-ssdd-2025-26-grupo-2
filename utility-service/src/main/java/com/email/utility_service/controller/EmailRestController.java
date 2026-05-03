package com.email.utility_service.controller;

import com.email.utility_service.dto.EmailRequestDTO;
import com.email.utility_service.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
public class EmailRestController {

    private final EmailService emailService;

    public EmailRestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody EmailRequestDTO request) {
        emailService.sendEmail(request);
        return ResponseEntity.noContent().build();
    }
}

