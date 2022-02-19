package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.AddressDto;
import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.entities.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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

    @BeforeAll
    static void init() {
        CountryMapper countryMapper = Mappers.getMapper(CountryMapper.class);
        // inject countryMapper for testing purpose, because autowire is not working
        ReflectionTestUtils.setField(addressMapper, "countryMapper", countryMapper);
    }

    @ParameterizedTest
    @MethodSource("providerForEntity")
    void toDto(Address address, Long id) {
        assertThat(addressMapper.toDto(address))
                .returns(id, AddressDto::getId)
                .returns(STREET, AddressDto::getStreet)
                .returns(CITY, AddressDto::getCity)
                .returns(ZIP_CODE, AddressDto::getZipCode);

        assertThat(addressMapper.toDto(address).getCountryDto())
                .returns(id, CountryDto::getId)
                .returns(COUNTRY_NAME, CountryDto::getName)
                .returns(ISO_3166_ALPHA_3, CountryDto::getIso3166alpha3);
    }

    @ParameterizedTest
    @MethodSource("providerForDto")
    void toEntity(AddressDto addressDto, Long id) {
        assertThat(addressMapper.toEntity(addressDto))
                .returns(id, Address::getId)
                .returns(STREET, Address::getStreet)
                .returns(CITY, Address::getCity)
                .returns(ZIP_CODE, Address::getZipCode);

        //TODO: addressMapper.toEntity(addressDto).getCountry() is null, possible options
        //1. rewrite mapper for load data from database
        //2. moved logic of load data from DB to service and test service instead of mapper
        assertThat(addressMapper.toEntity(addressDto).getCountry())
                .returns(id, Country::getId)
                .returns(COUNTRY_NAME, Country::getName)
                .returns(ISO_3166_ALPHA_3, Country::getIso3166alpha3);
    }

    private static Stream<Arguments> providerForEntity() {
        return Stream.of(
                Arguments.of(new Address(ID_POSITIVE, STREET, CITY, ZIP_CODE, new Country(ID_POSITIVE, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())), ID_POSITIVE),
                Arguments.of(new Address(ID_NEGATIVE, STREET, CITY, ZIP_CODE, new Country(ID_NEGATIVE, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())), ID_NEGATIVE),
                Arguments.of(new Address(Long.MIN_VALUE, STREET, CITY, ZIP_CODE, new Country(Long.MIN_VALUE, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())), Long.MIN_VALUE),
                Arguments.of(new Address(Long.MIN_VALUE, STREET, CITY, ZIP_CODE, new Country(Long.MIN_VALUE, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())), Long.MIN_VALUE)
        );
    }

    private static Stream<Arguments> providerForDto() {
        return Stream.of(
                Arguments.of(new AddressDto(ID_POSITIVE, new CountryDto(ID_POSITIVE, COUNTRY_NAME, ISO_3166_ALPHA_3), 1L, STREET, CITY, ZIP_CODE), ID_POSITIVE),
                Arguments.of(new AddressDto(ID_NEGATIVE, new CountryDto(ID_NEGATIVE, COUNTRY_NAME, ISO_3166_ALPHA_3), 1L, STREET, CITY, ZIP_CODE), ID_NEGATIVE),
                Arguments.of(new AddressDto(Long.MAX_VALUE, new CountryDto(Long.MAX_VALUE, COUNTRY_NAME, ISO_3166_ALPHA_3), 1L, STREET, CITY, ZIP_CODE), Long.MAX_VALUE),
                Arguments.of(new AddressDto(Long.MIN_VALUE, new CountryDto(ID_POSITIVE, COUNTRY_NAME, ISO_3166_ALPHA_3), 1L, STREET, CITY, ZIP_CODE), Long.MIN_VALUE)
        );
    }
}