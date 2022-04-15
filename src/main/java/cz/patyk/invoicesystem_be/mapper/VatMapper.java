package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.VatDto;
import cz.patyk.invoicesystem_be.entities.Vat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VatMapper {
    @Mapping(source = "default", target = "isDefault")
    Vat toEntity(VatDto vatDto);

    @Mapping(source = "default", target = "isDefault")
    VatDto toDto(Vat vat);
}
