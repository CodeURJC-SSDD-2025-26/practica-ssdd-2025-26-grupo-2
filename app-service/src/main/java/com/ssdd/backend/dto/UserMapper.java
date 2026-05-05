package com.ssdd.backend.dto;

import com.ssdd.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(source = "imagenPerfil.id", target = "imagenPerfilId")
    UserDTO toDTO(User user);

    User toEntity(UserCreateDTO dto);
}