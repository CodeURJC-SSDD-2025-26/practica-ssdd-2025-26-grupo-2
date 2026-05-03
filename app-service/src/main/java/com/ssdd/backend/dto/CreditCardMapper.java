package com.ssdd.backend.dto;

import com.ssdd.backend.model.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "ultimosCuatro", expression = "java(card.getUltimosCuatro())")
    CreditCardDTO toDTO(CreditCard card);

    @Mapping(target = "user", ignore = true)
    CreditCard toEntity(CreditCardDTO dto);

}
