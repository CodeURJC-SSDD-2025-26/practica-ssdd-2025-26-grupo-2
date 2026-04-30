package com.ssdd.backend.dto;

public record CreditCardDTO(
        Long id,
        Long userId,
        String titular,
        String numTarjeta,
        String cvv,
        String caducidad,
        String ultimosCuatro
) {}