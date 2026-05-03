package com.ssdd.backend.dto;

import com.ssdd.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Mapeamos el ID de la entidad ImagenPerfil al campo Long del DTO
    @Mapping(source = "imagenPerfil.id", target = "imagenPerfilId")
    UserDTO toDTO(User user);

    // Mapeo inverso: del ID del DTO volvemos a la entidad
    @Mapping(source = "imagenPerfilId", target = "imagenPerfil.id")
    User toEntity(UserDTO dto);
}