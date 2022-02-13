package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.AddressDto;
import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.entities.Country;
import cz.patyk.invoicesystem_be.mapper.AddressMapper;
import cz.patyk.invoicesystem_be.mapper.CountryMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class AddressServicesTest {
    @InjectMocks
    private static final AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);

    @BeforeAll
    static void init() {
        CountryMapper countryMapper = Mappers.getMapper(CountryMapper.class);
        // inject countryMapper for testing purpose, because autowire is not working
        ReflectionTestUtils.setField(addressMapper, "countryMapper", countryMapper);
    }


    @Test
    void getAllAddresses() {
        Country country = new Country();
        country.setId(Long.MIN_VALUE);
        country.setName("Val Verde");
        country.setIso3166alpha3("VV");

        Address address = new Address();
        address.setId(Long.MIN_VALUE);
        address.setCountry(country);
        address.setStreet("Fake street 123");
        address.setCity("Springfield");
        address.setZipCode("12345");

        AddressDto addressDto = addressMapper.toDto(address);

        assertEquals(address.getId(), addressDto.getId());
        assertEquals(address.getStreet(), addressDto.getStreet());
        assertEquals(address.getCity(), addressDto.getCity());
        assertEquals(address.getZipCode(), addressDto.getZipCode());
        assertEquals(address.getCountry().getId(), addressDto.getCountryId());
        assertEquals(address.getCountry().getId(), country.getId());
        assertEquals(address.getCountry().getName(), country.getName());
        assertEquals(address.getCountry().getIso3166alpha3(), country.getIso3166alpha3());
    }
}