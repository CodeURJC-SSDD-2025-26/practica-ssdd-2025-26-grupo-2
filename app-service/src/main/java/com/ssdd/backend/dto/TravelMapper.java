package com.ssdd.backend.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ssdd.backend.model.Travel;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TravelMapper {

    TravelDTO toDTO(Travel travel);

    List<TravelDTO> toDTOs(Collection<Travel> travels);

    @Mapping(target = "imagen", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    Travel toEntity(TravelDTO travelDTO);
}