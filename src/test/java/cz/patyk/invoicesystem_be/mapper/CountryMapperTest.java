package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.entities.Country;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Stream;

import static cz.patyk.invoicesystem_be.common.TestConstants.COUNTRY_TEST_ISO_3166_ALPHA_3;
import static cz.patyk.invoicesystem_be.common.TestConstants.COUNTRY_TEST_NAME;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_NEGATIVE;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_POSITIVE;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_ZERO;
import static org.assertj.core.api.Assertions.assertThat;

class CountryMapperTest {
    private static final CountryMapper countryMapper = Mappers.getMapper(CountryMapper.class);

    @ParameterizedTest
    @MethodSource("providerEntities")
    void toDto(Country country) {
        assertThat(countryMapper.toDto(country))
                .returns(country.getId(), CountryDto::getId)
                .returns(country.getName(), CountryDto::getName)
                .returns(country.getIso3166alpha3(), CountryDto::getIso3166alpha3);
    }

    @ParameterizedTest
    @MethodSource("providerDto")
    void toEntity(CountryDto countryDto) {
        assertThat(countryMapper.toEntity(countryDto))
                .returns(countryDto.getId(), Country::getId)
                .returns(countryDto.getName(), Country::getName)
                .returns(countryDto.getIso3166alpha3(), Country::getIso3166alpha3);
    }

    private static Stream<Arguments> providerEntities() {
        return Stream.of(
                Arguments.of(Country.builder().id(Long.MIN_VALUE).name(COUNTRY_TEST_NAME).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).addressList(List.of()).build()),
                Arguments.of(Country.builder().id(LONG_NEGATIVE).name(COUNTRY_TEST_NAME).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).addressList(List.of()).build()),
                Arguments.of(Country.builder().id(LONG_ZERO).name(COUNTRY_TEST_NAME).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).addressList(List.of()).build()),
                Arguments.of(Country.builder().id(LONG_POSITIVE).name(COUNTRY_TEST_NAME).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).addressList(List.of()).build()),
                Arguments.of(Country.builder().id(Long.MAX_VALUE).name(COUNTRY_TEST_NAME).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).addressList(List.of()).build()),
                Arguments.of(Country.builder().id(Long.MAX_VALUE).name(null).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).addressList(List.of()).build()),
                Arguments.of(Country.builder().id(Long.MAX_VALUE).name(COUNTRY_TEST_NAME).iso3166alpha3(null).addressList(List.of()).build()),
                Arguments.of(Country.builder().id(Long.MAX_VALUE).name(null).iso3166alpha3(null).addressList(List.of()).build())
        );
    }

    private static Stream<Arguments> providerDto() {
        return Stream.of(
                Arguments.of(CountryDto.builder().id(Long.MIN_VALUE).name(COUNTRY_TEST_NAME).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).build()),
                Arguments.of(CountryDto.builder().id(LONG_NEGATIVE).name(COUNTRY_TEST_NAME).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).build()),
                Arguments.of(CountryDto.builder().id(LONG_ZERO).name(COUNTRY_TEST_NAME).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).build()),
                Arguments.of(CountryDto.builder().id(LONG_POSITIVE).name(COUNTRY_TEST_NAME).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).build()),
                Arguments.of(CountryDto.builder().id(Long.MAX_VALUE).name(COUNTRY_TEST_NAME).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).build()),
                Arguments.of(CountryDto.builder().id(Long.MAX_VALUE).name(null).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).build()),
                Arguments.of(CountryDto.builder().id(Long.MAX_VALUE).name(null).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).build()),
                Arguments.of(CountryDto.builder().id(Long.MAX_VALUE).name(null).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).build())
        );
    }
}