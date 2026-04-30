package com.ssdd.backend.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ssdd.backend.model.Travel;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TravelMapper {

    // 1. De Entidad a DTO (Para enviar datos fuera de la API)
    @Mapping(source = "tieneImagen", target = "hasImage")
    TravelDTO toDTO(Travel travel);

    // 2. De Lista de Entidades a Lista de DTOs (Para el catálogo)
    List<TravelDTO> toDTOs(Collection<Travel> travels);

    // 3. De DTO a Entidad (Para recibir datos al crear/modificar un viaje)
    @Mapping(target = "imagen", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    Travel toDomain(TravelDTO travelDTO);
}