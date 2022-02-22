package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.out.AddressDtoOut;
import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.dto.in.AddressDtoIn;
import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.entities.Country;
import cz.patyk.invoicesystem_be.repositories.CountryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
        CountryRepository countryRepository = Mockito.mock(CountryRepository.class);
        // inject countryMapper for testing purpose, because autowire is not working
        ReflectionTestUtils.setField(addressMapper, "countryMapper", countryMapper);
        ReflectionTestUtils.setField(addressMapper, "countryRepository", countryRepository);
    }

    @ParameterizedTest
    @MethodSource("providerEntities")
    void toDto(Address address, Long id) {
        assertThat(addressMapper.toDto(address))
                .returns(id, AddressDtoOut::getId)
                .returns(STREET, AddressDtoOut::getStreet)
                .returns(CITY, AddressDtoOut::getCity)
                .returns(ZIP_CODE, AddressDtoOut::getZipCode);

        assertThat(addressMapper.toDto(address).getCountryDto())
                .returns(id, CountryDto::getId)
                .returns(COUNTRY_NAME, CountryDto::getName)
                .returns(ISO_3166_ALPHA_3, CountryDto::getIso3166alpha3);
    }

    @ParameterizedTest
    @MethodSource("providerDtos")
    void toEntity(AddressDtoIn addressDto, Long id) {
        Country country = new Country();
        country.setId(id);
        country.setName(COUNTRY_NAME);
        country.setIso3166alpha3(ISO_3166_ALPHA_3);

        Mockito
                .when(addressMapper.countryRepository.getById(addressDto.getCountry()))
                .thenReturn(country);

        assertThat(addressMapper.toEntity(addressDto))
                .returns(id, Address::getId)
                .returns(STREET, Address::getStreet)
                .returns(CITY, Address::getCity)
                .returns(ZIP_CODE, Address::getZipCode);

        assertThat(addressMapper.toEntity(addressDto).getCountry())
                .returns(id, Country::getId)
                .returns(COUNTRY_NAME, Country::getName)
                .returns(ISO_3166_ALPHA_3, Country::getIso3166alpha3);
    }

    private static Stream<Arguments> providerEntities() {
        return Stream.of(
                Arguments.of(new Address(ID_POSITIVE, STREET, CITY, ZIP_CODE, new Country(ID_POSITIVE, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())), ID_POSITIVE),
                Arguments.of(new Address(ID_NEGATIVE, STREET, CITY, ZIP_CODE, new Country(ID_NEGATIVE, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())), ID_NEGATIVE),
                Arguments.of(new Address(Long.MIN_VALUE, STREET, CITY, ZIP_CODE, new Country(Long.MIN_VALUE, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())), Long.MIN_VALUE),
                Arguments.of(new Address(Long.MIN_VALUE, STREET, CITY, ZIP_CODE, new Country(Long.MIN_VALUE, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())), Long.MIN_VALUE)
        );
    }

    private static Stream<Arguments> providerDtos() {
        return Stream.of(
                Arguments.of(new AddressDtoIn(ID_POSITIVE, 1L, STREET, CITY, ZIP_CODE), ID_POSITIVE),
                Arguments.of(new AddressDtoIn(ID_NEGATIVE, 1L, STREET, CITY, ZIP_CODE), ID_NEGATIVE),
                Arguments.of(new AddressDtoIn(Long.MAX_VALUE, 1L, STREET, CITY, ZIP_CODE), Long.MAX_VALUE),
                Arguments.of(new AddressDtoIn(Long.MIN_VALUE, 1L, STREET, CITY, ZIP_CODE), Long.MIN_VALUE)
        );
    }
}