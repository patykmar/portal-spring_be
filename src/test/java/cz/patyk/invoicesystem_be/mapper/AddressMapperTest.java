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
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.stream.Stream;

import static cz.patyk.invoicesystem_be.common.TestConstants.ADDRESS_TEST_CITY;
import static cz.patyk.invoicesystem_be.common.TestConstants.ADDRESS_TEST_STREET;
import static cz.patyk.invoicesystem_be.common.TestConstants.ADDRESS_TEST_ZIP_CODE;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_NEGATIVE;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_POSITIVE;
import static cz.patyk.invoicesystem_be.common.TestConstants.COUNTRY_TEST_ENTITY;


import static cz.patyk.invoicesystem_be.common.TestConstants.COUNTRY_TEST_ISO_3166_ALPHA_3;
import static cz.patyk.invoicesystem_be.common.TestConstants.COUNTRY_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;

class AddressMapperTest {
    private static final AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);

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
                .returns(ADDRESS_TEST_STREET, AddressDtoOut::getStreet)
                .returns(ADDRESS_TEST_CITY, AddressDtoOut::getCity)
                .returns(ADDRESS_TEST_ZIP_CODE, AddressDtoOut::getZipCode);

        assertThat(addressMapper.toDto(address).getCountryDto())
                .returns(id, CountryDto::getId)
                .returns(COUNTRY_TEST_NAME, CountryDto::getName)
                .returns(COUNTRY_TEST_ISO_3166_ALPHA_3, CountryDto::getIso3166alpha3);
    }

    @ParameterizedTest
    @MethodSource("providerDtos")
    void toEntity(AddressDtoIn addressDto, Long id) {
        Mockito
                .when(addressMapper.countryRepository.getById(addressDto.getCountry()))
                .thenReturn(COUNTRY_TEST_ENTITY);

        assertThat(addressMapper.toEntity(addressDto))
                .returns(id, Address::getId)
                .returns(ADDRESS_TEST_STREET, Address::getStreet)
                .returns(ADDRESS_TEST_CITY, Address::getCity)
                .returns(ADDRESS_TEST_ZIP_CODE, Address::getZipCode);

        assertThat(addressMapper.toEntity(addressDto).getCountry())
                .returns(COUNTRY_TEST_ENTITY.getId(), Country::getId)
                .returns(COUNTRY_TEST_NAME, Country::getName)
                .returns(COUNTRY_TEST_ISO_3166_ALPHA_3, Country::getIso3166alpha3);
    }

    private static Stream<Arguments> providerEntities() {
        return Stream.of(
                Arguments.of(new Address(LONG_POSITIVE, ADDRESS_TEST_STREET, ADDRESS_TEST_CITY, ADDRESS_TEST_ZIP_CODE, new Country(LONG_POSITIVE, COUNTRY_TEST_NAME, COUNTRY_TEST_ISO_3166_ALPHA_3, List.of())), LONG_POSITIVE),
                Arguments.of(new Address(LONG_NEGATIVE, ADDRESS_TEST_STREET, ADDRESS_TEST_CITY, ADDRESS_TEST_ZIP_CODE, new Country(LONG_NEGATIVE, COUNTRY_TEST_NAME, COUNTRY_TEST_ISO_3166_ALPHA_3, List.of())), LONG_NEGATIVE),
                Arguments.of(new Address(Long.MIN_VALUE, ADDRESS_TEST_STREET, ADDRESS_TEST_CITY, ADDRESS_TEST_ZIP_CODE, new Country(Long.MIN_VALUE, COUNTRY_TEST_NAME, COUNTRY_TEST_ISO_3166_ALPHA_3, List.of())), Long.MIN_VALUE),
                Arguments.of(new Address(Long.MIN_VALUE, ADDRESS_TEST_STREET, ADDRESS_TEST_CITY, ADDRESS_TEST_ZIP_CODE, new Country(Long.MIN_VALUE, COUNTRY_TEST_NAME, COUNTRY_TEST_ISO_3166_ALPHA_3, List.of())), Long.MIN_VALUE)
        );
    }

    private static Stream<Arguments> providerDtos() {
        return Stream.of(
                Arguments.of(new AddressDtoIn(LONG_POSITIVE, LONG_POSITIVE, ADDRESS_TEST_STREET, ADDRESS_TEST_CITY, ADDRESS_TEST_ZIP_CODE), LONG_POSITIVE),
                Arguments.of(new AddressDtoIn(LONG_NEGATIVE, LONG_POSITIVE, ADDRESS_TEST_STREET, ADDRESS_TEST_CITY, ADDRESS_TEST_ZIP_CODE), LONG_NEGATIVE),
                Arguments.of(new AddressDtoIn(Long.MAX_VALUE, LONG_POSITIVE, ADDRESS_TEST_STREET, ADDRESS_TEST_CITY, ADDRESS_TEST_ZIP_CODE), Long.MAX_VALUE),
                Arguments.of(new AddressDtoIn(Long.MIN_VALUE, LONG_POSITIVE, ADDRESS_TEST_STREET, ADDRESS_TEST_CITY, ADDRESS_TEST_ZIP_CODE), Long.MIN_VALUE)
        );
    }
}