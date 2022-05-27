package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.dto.in.VatDtoIn;
import cz.patyk.invoicesystem_be.dto.out.VatDtoOut;
import cz.patyk.invoicesystem_be.entities.Vat;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

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
                Arguments.of(Vat.builder().id(Long.MIN_VALUE).name(Common.VAT_TEST_NAME).percent(Integer.MIN_VALUE).multiplier(Integer.MIN_VALUE).isDefault(true).build(), Long.MIN_VALUE, Integer.MIN_VALUE, true),
                Arguments.of(Vat.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.VAT_TEST_NAME).percent(NumberUtils.INTEGER_MINUS_ONE).multiplier(NumberUtils.INTEGER_MINUS_ONE).isDefault(false).build(), NumberUtils.LONG_MINUS_ONE, NumberUtils.INTEGER_MINUS_ONE, false),
                Arguments.of(Vat.builder().id(NumberUtils.LONG_ZERO).name(Common.VAT_TEST_NAME).percent(NumberUtils.INTEGER_ZERO).multiplier(NumberUtils.INTEGER_ZERO).isDefault(true).build(), NumberUtils.LONG_ZERO, NumberUtils.INTEGER_ZERO, true),
                Arguments.of(Vat.builder().id(NumberUtils.LONG_ONE).name(Common.VAT_TEST_NAME).percent(NumberUtils.INTEGER_ONE).multiplier(NumberUtils.INTEGER_ONE).isDefault(false).build(), NumberUtils.LONG_ONE, NumberUtils.INTEGER_ONE, false),
                Arguments.of(Vat.builder().id(Long.MAX_VALUE).name(Common.VAT_TEST_NAME).percent(Integer.MAX_VALUE).multiplier(Integer.MAX_VALUE).isDefault(true).build(), Long.MAX_VALUE, Integer.MAX_VALUE, true)
        );
    }

    private static Stream<Arguments> providerDtos() {
        return Stream.of(
                Arguments.of(VatDtoIn.builder().id(Long.MIN_VALUE).name(Common.VAT_TEST_NAME).percent(Integer.MIN_VALUE).isDefault(false).build(), Long.MIN_VALUE, Integer.MIN_VALUE, false),
                Arguments.of(VatDtoIn.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.VAT_TEST_NAME).percent(NumberUtils.INTEGER_MINUS_ONE).isDefault(true).build(), NumberUtils.LONG_MINUS_ONE, NumberUtils.INTEGER_MINUS_ONE, true),
                Arguments.of(VatDtoIn.builder().id(NumberUtils.LONG_ZERO).name(Common.VAT_TEST_NAME).percent(NumberUtils.INTEGER_ZERO).isDefault(false).build(), NumberUtils.LONG_ZERO, NumberUtils.INTEGER_ZERO, false),
                Arguments.of(VatDtoIn.builder().id(NumberUtils.LONG_ONE).name(Common.VAT_TEST_NAME).percent(NumberUtils.INTEGER_ONE).isDefault(true).build(), NumberUtils.LONG_ONE, NumberUtils.INTEGER_ONE, true),
                Arguments.of(VatDtoIn.builder().id(Long.MAX_VALUE).name(Common.VAT_TEST_NAME).percent(Integer.MAX_VALUE).isDefault(false).build(), Long.MAX_VALUE, Integer.MAX_VALUE, false)
        );
    }
}