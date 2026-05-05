package com.ssdd.backend.dto;

public record UserCreateDTO(
    String nombre,
    String email,
    String password,
    Boolean acceptTerms
) {}