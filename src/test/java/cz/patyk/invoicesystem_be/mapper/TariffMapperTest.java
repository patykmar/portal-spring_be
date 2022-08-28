package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.constant.TestEntities;
import cz.patyk.invoicesystem_be.dto.in.TariffDtoIn;
import cz.patyk.invoicesystem_be.dto.out.TariffDtoOut;
import cz.patyk.invoicesystem_be.dto.out.VatDtoOut;
import cz.patyk.invoicesystem_be.entities.Tariff;
import cz.patyk.invoicesystem_be.entities.Vat;
import cz.patyk.invoicesystem_be.service.VatService;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class TariffMapperTest {
    private static final TariffMapper TARIFF_MAPPER = Mappers.getMapper(TariffMapper.class);

    @BeforeAll
    static void init() {
        VatMapper vatMapper = Mappers.getMapper(VatMapper.class);
        VatService vatService = Mockito.mock(VatService.class);
        ReflectionTestUtils.setField(TARIFF_MAPPER, "vatMapper", vatMapper);
        ReflectionTestUtils.setField(TARIFF_MAPPER, "vatService", vatService);
    }

    @ParameterizedTest
    @MethodSource("providerDtos")
    void toEntity(TariffDtoIn tariffDtoIn, Long expectedLong) {
        Mockito
                .when(TARIFF_MAPPER.vatService.getEntityById(tariffDtoIn.getVat()))
                .thenReturn(TestEntities.VAT_01);

        assertThat(TARIFF_MAPPER.toEntity(tariffDtoIn))
                .returns(expectedLong, Tariff::getId)
                .returns(Common.TARIFF_TEST_NAME, Tariff::getName)
                .returns(expectedLong, Tariff::getPrice);

        assertThat(TARIFF_MAPPER.toEntity(tariffDtoIn).getVat())
                .returns(NumberUtils.LONG_ONE, Vat::getId)
                .returns(Common.VAT_TEST_NAME, Vat::getName)
                .returns(true, Vat::isDefault)
                .returns(Common.VAT_TEST_PERCENT_20, Vat::getPercent)
                .returns(Common.VAT_TEST_MULTIPLIER_120, Vat::getMultiplier);
    }

    @ParameterizedTest
    @MethodSource("providerEntities")
    void toDtoOut(Tariff tariff, Long expectedLong) {
        assertThat(TARIFF_MAPPER.toDtoOut(tariff))
                .returns(expectedLong, TariffDtoOut::getId)
                .returns(Common.TARIFF_TEST_NAME, TariffDtoOut::getName)
                .returns(expectedLong, TariffDtoOut::getPrice)
                .returns(NumberUtils.LONG_ONE, TariffDtoOut::getVat);

        assertThat(TARIFF_MAPPER.toDtoOut(tariff).getVatDto())
                .returns(NumberUtils.LONG_ONE, VatDtoOut::getId)
                .returns(Common.VAT_TEST_NAME, VatDtoOut::getName)
                .returns(true, VatDtoOut::isDefault)
                .returns(Common.VAT_TEST_PERCENT_20, VatDtoOut::getPercent)
                .returns(Common.VAT_TEST_MULTIPLIER_120, VatDtoOut::getMultiplier);
    }

    private static Stream<Arguments> providerEntities() {
        return Stream.of(
                Arguments.of(Tariff.builder().id(Long.MIN_VALUE).name(Common.TARIFF_TEST_NAME).vat(TestEntities.VAT_01).price(Long.MIN_VALUE).build(), Long.MIN_VALUE),
                Arguments.of(Tariff.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.TARIFF_TEST_NAME).vat(TestEntities.VAT_01).price(NumberUtils.LONG_MINUS_ONE).build(), NumberUtils.LONG_MINUS_ONE),
                Arguments.of(Tariff.builder().id(NumberUtils.LONG_ZERO).name(Common.TARIFF_TEST_NAME).vat(TestEntities.VAT_01).price(NumberUtils.LONG_ZERO).build(), NumberUtils.LONG_ZERO),
                Arguments.of(Tariff.builder().id(NumberUtils.LONG_ONE).name(Common.TARIFF_TEST_NAME).vat(TestEntities.VAT_01).price(NumberUtils.LONG_ONE).build(), NumberUtils.LONG_ONE),
                Arguments.of(Tariff.builder().id(Long.MAX_VALUE).name(Common.TARIFF_TEST_NAME).vat(TestEntities.VAT_01).price(Long.MAX_VALUE).build(), Long.MAX_VALUE)
        );
    }

    private static Stream<Arguments> providerDtos() {
        return Stream.of(
                Arguments.of(TariffDtoIn.builder().id(Long.MIN_VALUE).name(Common.TARIFF_TEST_NAME).vat(NumberUtils.LONG_ONE).price(Long.MIN_VALUE).build(), Long.MIN_VALUE),
                Arguments.of(TariffDtoIn.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.TARIFF_TEST_NAME).vat(NumberUtils.LONG_ONE).price(NumberUtils.LONG_MINUS_ONE).build(), NumberUtils.LONG_MINUS_ONE),
                Arguments.of(TariffDtoIn.builder().id(NumberUtils.LONG_ZERO).name(Common.TARIFF_TEST_NAME).vat(NumberUtils.LONG_ONE).price(NumberUtils.LONG_ZERO).build(), NumberUtils.LONG_ZERO),
                Arguments.of(TariffDtoIn.builder().id(NumberUtils.LONG_ONE).name(Common.TARIFF_TEST_NAME).vat(NumberUtils.LONG_ONE).price(NumberUtils.LONG_ONE).build(), NumberUtils.LONG_ONE),
                Arguments.of(TariffDtoIn.builder().id(Long.MAX_VALUE).name(Common.TARIFF_TEST_NAME).vat(NumberUtils.LONG_ONE).price(Long.MAX_VALUE).build(), Long.MAX_VALUE)
        );
    }
}