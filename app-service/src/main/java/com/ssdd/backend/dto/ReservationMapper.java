package com.ssdd.backend.dto;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ssdd.backend.model.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "viaje.id", target = "viajeId")
    @Mapping(source = "imagen.id", target = "imagenId")
    ReservationDTO toDTO(Reservation reservation);

    List<ReservationDTO> toDTOs(Collection<Reservation> reservations);

    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "viaje", ignore = true)
    @Mapping(target = "imagen", ignore = true)
    Reservation toEntity(ReservationDTO reservationDTO);
}