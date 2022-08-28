package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.AddressDtoIn;
import cz.patyk.invoicesystem_be.dto.out.AddressDtoOut;
import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.entities.Country;
import cz.patyk.invoicesystem_be.service.CountryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public abstract class AddressMapper {
    @Autowired
    protected CountryService countryService;

    @Mapping(target = "country", source = "country.id")
    @Mapping(target = "countryDto", source = "address.country")
    public abstract AddressDtoOut toDto(Address address);

    @Mapping(target = "country", expression = "java(getCountry(addressDto.getCountry()))")
    public abstract Address toEntity(AddressDtoIn addressDto);

    public Country getCountry(Long country) {
        return countryService.getEntityById(country);
    }
}
