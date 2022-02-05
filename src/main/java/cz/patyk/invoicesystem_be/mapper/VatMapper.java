package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.VatDto;
import cz.patyk.invoicesystem_be.entities.Vat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VatMapper {
    Vat toEntity(VatDto vatDto);

    VatDto toDto(Vat vat);
}
