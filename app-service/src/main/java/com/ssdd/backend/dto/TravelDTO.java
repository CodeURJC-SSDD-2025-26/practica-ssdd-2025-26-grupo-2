package com.ssdd.backend.dto;

import java.time.LocalDate;

public record TravelDTO(
        Long id,
        String nombre,
        String descripcion,
        String pais,
        double precio,
        String transporte,
        String alojamiento,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        int maxPlazas,
        int numNoches,
        ImageDTO imagen) {}