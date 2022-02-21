package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.AddressDtoIn;
import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.dto.AddressDto;
import cz.patyk.invoicesystem_be.entities.Country;
import cz.patyk.invoicesystem_be.repositories.CountryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public abstract class AddressMapper {

    @Autowired
    protected CountryRepository countryRepository;

    @Mapping(target = "countryId", source = "country.id")
    @Mapping(target = "countryDto", source = "address.country")
    public abstract AddressDto toDto(Address address);

    @Mapping(target = "country", expression = "java(getCountry(addressDto.getCountry()))")
    public abstract Address toEntity(AddressDtoIn addressDto);

    public Country getCountry(Long country) {
        return countryRepository.getById(country);
    }
}
