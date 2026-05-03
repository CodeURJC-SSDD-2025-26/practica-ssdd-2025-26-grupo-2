package com.ssdd.backend.dto;

public record EmailRequestDTO(
    String to,
    String subject,
    String body
) {
}
