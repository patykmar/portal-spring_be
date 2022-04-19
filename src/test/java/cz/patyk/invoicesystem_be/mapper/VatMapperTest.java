package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.VatDtoIn;
import cz.patyk.invoicesystem_be.dto.out.VatDtoOut;
import cz.patyk.invoicesystem_be.entities.Vat;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static cz.patyk.invoicesystem_be.common.TestConstants.VAT_TEST_NAME;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;
import static org.apache.commons.lang3.math.NumberUtils.LONG_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;
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
                Arguments.of(Vat.builder().id(Long.MIN_VALUE).name(VAT_TEST_NAME).percent(Integer.MIN_VALUE).multiplier(Integer.MIN_VALUE).isDefault(true).build(), Long.MIN_VALUE, Integer.MIN_VALUE, true),
                Arguments.of(Vat.builder().id(LONG_MINUS_ONE).name(VAT_TEST_NAME).percent(INTEGER_MINUS_ONE).multiplier(INTEGER_MINUS_ONE).isDefault(false).build(), LONG_MINUS_ONE, INTEGER_MINUS_ONE, false),
                Arguments.of(Vat.builder().id(LONG_ZERO).name(VAT_TEST_NAME).percent(INTEGER_ZERO).multiplier(INTEGER_ZERO).isDefault(true).build(), LONG_ZERO, INTEGER_ZERO, true),
                Arguments.of(Vat.builder().id(LONG_ONE).name(VAT_TEST_NAME).percent(INTEGER_ONE).multiplier(INTEGER_ONE).isDefault(false).build(), LONG_ONE, INTEGER_ONE, false),
                Arguments.of(Vat.builder().id(Long.MAX_VALUE).name(VAT_TEST_NAME).percent(Integer.MAX_VALUE).multiplier(Integer.MAX_VALUE).isDefault(true).build(), Long.MAX_VALUE, Integer.MAX_VALUE, true)
        );
    }

    private static Stream<Arguments> providerDtos() {
        return Stream.of(
                Arguments.of(VatDtoIn.builder().id(Long.MIN_VALUE).name(VAT_TEST_NAME).percent(Integer.MIN_VALUE).isDefault(false).build(), Long.MIN_VALUE, Integer.MIN_VALUE, false),
                Arguments.of(VatDtoIn.builder().id(LONG_MINUS_ONE).name(VAT_TEST_NAME).percent(INTEGER_MINUS_ONE).isDefault(true).build(), LONG_MINUS_ONE, INTEGER_MINUS_ONE, true),
                Arguments.of(VatDtoIn.builder().id(LONG_ZERO).name(VAT_TEST_NAME).percent(INTEGER_ZERO).isDefault(false).build(), LONG_ZERO, INTEGER_ZERO, false),
                Arguments.of(VatDtoIn.builder().id(LONG_ONE).name(VAT_TEST_NAME).percent(INTEGER_ONE).isDefault(true).build(), LONG_ONE, INTEGER_ONE, true),
                Arguments.of(VatDtoIn.builder().id(Long.MAX_VALUE).name(VAT_TEST_NAME).percent(Integer.MAX_VALUE).isDefault(false).build(), Long.MAX_VALUE, Integer.MAX_VALUE, false)
        );
    }
}