package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface AddressMapper {

    @Mapping(target = "countryId", source = "country.id")
    @Mapping(target = "countryDto", source = "address.country")
    AddressDto toDto(Address address);

    Address toEntity(AddressDto addressDto);
}
