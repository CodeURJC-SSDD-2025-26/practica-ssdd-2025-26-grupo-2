package com.ssdd.backend.dto;

import java.time.LocalDate;

import com.ssdd.backend.model.Reservation.Estado;

public record ReservationDTO(
        Long id,
        Long usuarioId,
        Long viajeId,
        int numeroPersonas,
        LocalDate fechaReserva,
        double precioTotal,
        Estado estado,
        Long imagenId
) {
}