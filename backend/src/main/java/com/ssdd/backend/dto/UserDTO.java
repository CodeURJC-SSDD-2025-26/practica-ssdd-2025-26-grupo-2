package com.ssdd.backend.dto;

import java.util.List;

public record UserDTO(
    Long id,
    String nombre,
    String email,
    String password,
    List<String> roles,
    Long imagenPerfilId,
    Long creditCardId,
    Boolean acceptTerms
) {}