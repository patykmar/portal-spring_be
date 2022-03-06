package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.entities.Country;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CountryMapperTest {
    @InjectMocks
    private static final CountryMapper countryMapper = Mappers.getMapper(CountryMapper.class);

    public static final String COUNTRY_NAME = "Val Verde";
    public static final String ISO_3166_ALPHA_3 = "VV";


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
                Arguments.of(new Country(Long.MIN_VALUE, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())),
                Arguments.of(new Country(-1L, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())),
                Arguments.of(new Country(0L, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())),
                Arguments.of(new Country(1L, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())),
                Arguments.of(new Country(Long.MAX_VALUE, COUNTRY_NAME, ISO_3166_ALPHA_3, List.of())),
                Arguments.of(new Country(Long.MAX_VALUE, null, ISO_3166_ALPHA_3, List.of())),
                Arguments.of(new Country(Long.MAX_VALUE, COUNTRY_NAME, null, List.of())),
                Arguments.of(new Country(Long.MAX_VALUE, null, null, List.of()))
        );
    }

    private static Stream<Arguments> providerDto() {
        return Stream.of(
                Arguments.of(new CountryDto(Long.MIN_VALUE, COUNTRY_NAME, ISO_3166_ALPHA_3)),
                Arguments.of(new CountryDto(-1L, COUNTRY_NAME, ISO_3166_ALPHA_3)),
                Arguments.of(new CountryDto(0L, COUNTRY_NAME, ISO_3166_ALPHA_3)),
                Arguments.of(new CountryDto(1L, COUNTRY_NAME, ISO_3166_ALPHA_3)),
                Arguments.of(new CountryDto(Long.MAX_VALUE, COUNTRY_NAME, ISO_3166_ALPHA_3)),
                Arguments.of(new CountryDto(Long.MAX_VALUE, null, ISO_3166_ALPHA_3)),
                Arguments.of(new CountryDto(Long.MAX_VALUE, COUNTRY_NAME, null)),
                Arguments.of(new CountryDto(Long.MAX_VALUE, null, null))
        );
    }
}