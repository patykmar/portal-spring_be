package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.TariffDto;
import cz.patyk.invoicesystem_be.entities.Tariff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TariffMapper {
    Tariff toEntity(TariffDto tariffDto);

    TariffDto toDto(Tariff tariff);
}
