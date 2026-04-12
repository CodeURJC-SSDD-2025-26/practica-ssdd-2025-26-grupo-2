package com.ssdd.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public void sendContactEmail(String nombre, String apellidos, String email, String mensaje) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(emailFrom);
        message.setSubject("Nuevo mensaje de contacto - " + nombre + " " + apellidos);
        message.setText(
            "Nombre: " + nombre + " " + apellidos + "\n" +
            "Email: " + email + "\n\n" +
            "Mensaje:\n" + mensaje
        );
        mailSender.send(message);
    }
}
