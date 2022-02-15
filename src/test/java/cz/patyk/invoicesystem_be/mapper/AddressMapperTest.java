package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.AddressDto;
import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.entities.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {
    @InjectMocks
    private static final AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);

    private static final Long ID_POSITIVE = 1L;
    private static final Long ID_NEGATIVE = -1L;
    private static final String COUNTRY_NAME = "Val Verde";
    private static final String ISO_3166_ALPHA_3 = "VV";
    private static final String STREET = "Fake street 123";
    private static final String CITY = "Springfield";
    private static final String ZIP_CODE = "12345";

    private static final Country country = new Country();
    private static final Address address = new Address();

    @BeforeAll
    static void init() {
        CountryMapper countryMapper = Mappers.getMapper(CountryMapper.class);
        // inject countryMapper for testing purpose, because autowire is not working
        ReflectionTestUtils.setField(addressMapper, "countryMapper", countryMapper);
    }

    @BeforeAll
    static void setUpEntityObjects(){
        country.setId(ID_POSITIVE);
        country.setName(COUNTRY_NAME);
        country.setIso3166alpha3(ISO_3166_ALPHA_3);

        address.setId(ID_POSITIVE);
        address.setCountry(country);
        address.setStreet(STREET);
        address.setCity(CITY);
        address.setZipCode(ZIP_CODE);
    }

    @Test
    void toDto() {
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

    @Test
    void toEntity() {
        CountryDto countryDto = CountryDto.builder()
                .id(ID_POSITIVE)
                .name(COUNTRY_NAME)
                .iso3166alpha3(ISO_3166_ALPHA_3)
                .build();

        AddressDto addressDto = AddressDto.builder()
                .id(ID_POSITIVE)
                .countryDto(countryDto)
                .street(STREET)
                .city(CITY)
                .zipCode(ZIP_CODE)
                .build();

        Address address = addressMapper.toEntity(addressDto);

        assertEquals(ID_POSITIVE, address.getId());
    }
}