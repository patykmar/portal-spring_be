package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.constant.TestEntities;
import cz.patyk.invoicesystem_be.dto.out.AddressDtoOut;
import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.dto.in.AddressDtoIn;
import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.entities.Country;
import cz.patyk.invoicesystem_be.service.CountryService;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AddressMapperTest {
    private static final AddressMapper ADDRESS_MAPPER = Mappers.getMapper(AddressMapper.class);

    @BeforeAll
    static void init() {
        CountryMapper countryMapper = Mappers.getMapper(CountryMapper.class);
        CountryService countryService = Mockito.mock(CountryService.class);
        // inject countryMapper for testing purpose, because autowire is not working
        ReflectionTestUtils.setField(ADDRESS_MAPPER, "countryMapper", countryMapper);
        ReflectionTestUtils.setField(ADDRESS_MAPPER, "countryService", countryService);
    }

    @ParameterizedTest
    @MethodSource("providerEntities")
    void toDto(Address address, Long id) {
        assertThat(ADDRESS_MAPPER.toDto(address))
                .returns(id, AddressDtoOut::getId)
                .returns(Common.ADDRESS_TEST_STREET, AddressDtoOut::getStreet)
                .returns(Common.ADDRESS_TEST_CITY, AddressDtoOut::getCity)
                .returns(Common.ADDRESS_TEST_ZIP_CODE, AddressDtoOut::getZipCode);

        assertThat(ADDRESS_MAPPER.toDto(address).getCountryDto())
                .returns(id, CountryDto::getId)
                .returns(Common.COUNTRY_TEST_NAME, CountryDto::getName)
                .returns(Common.COUNTRY_TEST_ISO_3166_ALPHA_3, CountryDto::getIso3166alpha3);
    }

    @ParameterizedTest
    @MethodSource("providerDtos")
    void toEntity(AddressDtoIn addressDto, Long id) {
        Mockito
                .when(ADDRESS_MAPPER.countryService.getEntityById(addressDto.getCountry()))
                .thenReturn(TestEntities.COUNTRY_TEST);

        assertThat(ADDRESS_MAPPER.toEntity(addressDto))
                .returns(id, Address::getId)
                .returns(Common.ADDRESS_TEST_STREET, Address::getStreet)
                .returns(Common.ADDRESS_TEST_CITY, Address::getCity)
                .returns(Common.ADDRESS_TEST_ZIP_CODE, Address::getZipCode);

        assertThat(ADDRESS_MAPPER.toEntity(addressDto).getCountry())
                .returns(TestEntities.COUNTRY_TEST.getId(), Country::getId)
                .returns(Common.COUNTRY_TEST_NAME, Country::getName)
                .returns(Common.COUNTRY_TEST_ISO_3166_ALPHA_3, Country::getIso3166alpha3);
    }

    private static Stream<Arguments> providerEntities() {
        return Stream.of(
                Arguments.of(new Address(NumberUtils.LONG_ONE, Common.ADDRESS_TEST_STREET, Common.ADDRESS_TEST_CITY, Common.ADDRESS_TEST_ZIP_CODE, new Country(NumberUtils.LONG_ONE, Common.COUNTRY_TEST_NAME, Common.COUNTRY_TEST_ISO_3166_ALPHA_3, List.of())), NumberUtils.LONG_ONE),
                Arguments.of(new Address(NumberUtils.LONG_MINUS_ONE, Common.ADDRESS_TEST_STREET, Common.ADDRESS_TEST_CITY, Common.ADDRESS_TEST_ZIP_CODE, new Country(NumberUtils.LONG_MINUS_ONE, Common.COUNTRY_TEST_NAME, Common.COUNTRY_TEST_ISO_3166_ALPHA_3, List.of())), NumberUtils.LONG_MINUS_ONE),
                Arguments.of(new Address(Long.MIN_VALUE, Common.ADDRESS_TEST_STREET, Common.ADDRESS_TEST_CITY, Common.ADDRESS_TEST_ZIP_CODE, new Country(Long.MIN_VALUE, Common.COUNTRY_TEST_NAME, Common.COUNTRY_TEST_ISO_3166_ALPHA_3, List.of())), Long.MIN_VALUE),
                Arguments.of(new Address(Long.MIN_VALUE, Common.ADDRESS_TEST_STREET, Common.ADDRESS_TEST_CITY, Common.ADDRESS_TEST_ZIP_CODE, new Country(Long.MIN_VALUE, Common.COUNTRY_TEST_NAME, Common.COUNTRY_TEST_ISO_3166_ALPHA_3, List.of())), Long.MIN_VALUE)
        );
    }

    private static Stream<Arguments> providerDtos() {
        return Stream.of(
                Arguments.of(new AddressDtoIn(NumberUtils.LONG_ONE, NumberUtils.LONG_ONE, Common.ADDRESS_TEST_STREET, Common.ADDRESS_TEST_CITY, Common.ADDRESS_TEST_ZIP_CODE), NumberUtils.LONG_ONE),
                Arguments.of(new AddressDtoIn(NumberUtils.LONG_MINUS_ONE, NumberUtils.LONG_ONE, Common.ADDRESS_TEST_STREET, Common.ADDRESS_TEST_CITY, Common.ADDRESS_TEST_ZIP_CODE), NumberUtils.LONG_MINUS_ONE),
                Arguments.of(new AddressDtoIn(Long.MAX_VALUE, NumberUtils.LONG_ONE, Common.ADDRESS_TEST_STREET, Common.ADDRESS_TEST_CITY, Common.ADDRESS_TEST_ZIP_CODE), Long.MAX_VALUE),
                Arguments.of(new AddressDtoIn(Long.MIN_VALUE, NumberUtils.LONG_ONE, Common.ADDRESS_TEST_STREET, Common.ADDRESS_TEST_CITY, Common.ADDRESS_TEST_ZIP_CODE), Long.MIN_VALUE)
        );
    }
}