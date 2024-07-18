package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.entities.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CountryMapper {
    CountryDto toDto(Country country);

    @Mapping(target = "addressList", ignore = true)
    Country toEntity(CountryDto countryDto);
}
