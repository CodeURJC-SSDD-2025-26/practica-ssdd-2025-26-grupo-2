package com.ssdd.backend.dto;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ssdd.backend.model.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {


    @Mapping(source = "autor.nombre", target = "nombreAutor")
    @Mapping(source = "viaje.id", target = "viajeId")
    ReviewDTO toDTO(Review review);

    List<ReviewDTO> toDTOs(Collection<Review> reviews);

    @Mapping(target = "autor", ignore = true)
    @Mapping(target = "viaje", ignore = true)
    Review toEntity(ReviewDTO reviewDTO);
}




