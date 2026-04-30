package com.ssdd.backend.dto;

import org.mapstruct.Mapper;

import com.ssdd.backend.model.Image;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageDTO toDTO(Image image);
    
}