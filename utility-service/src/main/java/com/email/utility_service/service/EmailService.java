package com.email.utility_service.service;

import com.email.utility_service.dto.EmailRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String emailFrom;

    public EmailService(
            JavaMailSender mailSender,
            @Value("${spring.mail.username}") String emailFrom
    ) {
        this.mailSender = mailSender;
        this.emailFrom = emailFrom;
    }

    public void sendEmail(EmailRequestDTO request) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailFrom);
        message.setTo(request.to());
        message.setSubject(request.subject());
        message.setText(request.body());

        mailSender.send(message);
    }
}

