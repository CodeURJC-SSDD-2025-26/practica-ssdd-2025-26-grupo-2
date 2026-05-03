package com.ssdd.backend.dto;

import java.util.List;

public record UserDTO(
    Long id,
    String nombre,
    String email,
    List<String> roles,
    Long imagenPerfilId,
    Boolean acceptTerms
) {}