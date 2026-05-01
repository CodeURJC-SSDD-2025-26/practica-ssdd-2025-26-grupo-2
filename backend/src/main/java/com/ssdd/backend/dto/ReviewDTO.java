package com.ssdd.backend.dto;

public record ReviewDTO (
    Long id,
    String comentario,
    Integer puntuacion,
    String nombreAutor,
    Long viajeId) {
    }
    
    



