package com.email.utility_service.dto;

public record EmailRequestDTO(
    String to,
    String subject,
    String body
) {
}

