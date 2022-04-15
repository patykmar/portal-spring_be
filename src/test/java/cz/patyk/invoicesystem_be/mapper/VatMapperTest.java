package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.VatDtoIn;
import cz.patyk.invoicesystem_be.dto.out.VatDtoOut;
import cz.patyk.invoicesystem_be.entities.Vat;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static cz.patyk.invoicesystem_be.common.TestConstants.*;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_NEGATIVE;
import static org.assertj.core.api.Assertions.assertThat;

class VatMapperTest {

    private static final VatMapper VAT_MAPPER = Mappers.getMapper(VatMapper.class);


    @ParameterizedTest
    @MethodSource("providerDtos")
    void toEntity(VatDtoIn vatDtoIn, Long idExpected, int intValuesExpected, boolean booleanExpected) {
        assertThat(VAT_MAPPER.toEntity(vatDtoIn))
                .returns(idExpected, Vat::getId)
                .returns(intValuesExpected, Vat::getPercent)
                .returns(intValuesExpected + Vat.MULTIPLIER_CONST, Vat::getMultiplier)
                .returns(booleanExpected, Vat::isDefault);
    }

    @ParameterizedTest
    @MethodSource("providerEntities")
    void toDto(Vat vat, Long idExpected, int intValuesExpected, boolean booleanExpected) {
        assertThat(VAT_MAPPER.toDto(vat))
                .returns(idExpected, VatDtoOut::getId)
                .returns(intValuesExpected, VatDtoOut::getPercent)
                .returns(intValuesExpected, VatDtoOut::getMultiplier)
                .returns(booleanExpected, VatDtoOut::isDefault);
    }

    private static Stream<Arguments> providerEntities() {
        return Stream.of(
                Arguments.of(Vat.builder().id(LONG_MIN).name(TEST_VAT_NAME).percent(INT_MIN).multiplier(INT_MIN).isDefault(true).build(), LONG_MIN, INT_MIN, true),
                Arguments.of(Vat.builder().id(LONG_NEGATIVE).name(TEST_VAT_NAME).percent(INT_NEGATIVE).multiplier(INT_NEGATIVE).isDefault(false).build(), LONG_NEGATIVE, INT_NEGATIVE, false),
                Arguments.of(Vat.builder().id(LONG_ZERO).name(TEST_VAT_NAME).percent(INT_ZERO).multiplier(INT_ZERO).isDefault(true).build(), LONG_ZERO, INT_ZERO, true),
                Arguments.of(Vat.builder().id(LONG_POSITIVE).name(TEST_VAT_NAME).percent(INT_POSITIVE).multiplier(INT_POSITIVE).isDefault(false).build(), LONG_POSITIVE, INT_POSITIVE, false),
                Arguments.of(Vat.builder().id(LONG_MAX).name(TEST_VAT_NAME).percent(INT_MAX).multiplier(INT_MAX).isDefault(true).build(), LONG_MAX, INT_MAX, true)
        );
    }

    private static Stream<Arguments> providerDtos() {
        return Stream.of(
                Arguments.of(VatDtoIn.builder().id(LONG_MIN).name(TEST_VAT_NAME).percent(INT_MIN).isDefault(false).build(), LONG_MIN, INT_MIN, false),
                Arguments.of(VatDtoIn.builder().id(LONG_NEGATIVE).name(TEST_VAT_NAME).percent(INT_NEGATIVE).isDefault(true).build(), LONG_NEGATIVE, INT_NEGATIVE, true),
                Arguments.of(VatDtoIn.builder().id(LONG_ZERO).name(TEST_VAT_NAME).percent(INT_ZERO).isDefault(false).build(), LONG_ZERO, INT_ZERO, false),
                Arguments.of(VatDtoIn.builder().id(LONG_POSITIVE).name(TEST_VAT_NAME).percent(INT_POSITIVE).isDefault(true).build(), LONG_POSITIVE, INT_POSITIVE, true),
                Arguments.of(VatDtoIn.builder().id(LONG_MAX).name(TEST_VAT_NAME).percent(INT_MAX).isDefault(false).build(), LONG_MAX, INT_MAX, false)
        );
    }
}