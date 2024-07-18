package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.VatDtoIn;
import cz.patyk.invoicesystem_be.dto.out.VatDtoOut;
import cz.patyk.invoicesystem_be.entities.Vat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface VatMapper {
    @Mapping(source = "default", target = "isDefault")
    @Mapping(target = "multiplier", expression = "java(vatDtoIn.getPercent() + Vat.MULTIPLIER_CONST)")
    Vat toEntity(VatDtoIn vatDtoIn);

    @Mapping(source = "default", target = "isDefault")
    VatDtoOut toDto(Vat vat);
}
