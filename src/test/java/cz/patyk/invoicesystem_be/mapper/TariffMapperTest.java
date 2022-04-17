package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.TariffDtoIn;
import cz.patyk.invoicesystem_be.dto.out.TariffDtoOut;
import cz.patyk.invoicesystem_be.dto.out.VatDtoOut;
import cz.patyk.invoicesystem_be.entities.Tariff;
import cz.patyk.invoicesystem_be.entities.Vat;
import cz.patyk.invoicesystem_be.repositories.VatRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.stream.Stream;

import static cz.patyk.invoicesystem_be.common.TestConstants.FK_VAT;
import static cz.patyk.invoicesystem_be.common.TestConstants.FK_VAT_MULTIPLIER;
import static cz.patyk.invoicesystem_be.common.TestConstants.FK_VAT_PERCENT;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_MAX;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_MIN;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_NEGATIVE;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_POSITIVE;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_ZERO;
import static cz.patyk.invoicesystem_be.common.TestConstants.TEST_TARIFF_NAME;
import static cz.patyk.invoicesystem_be.common.TestConstants.TEST_VAT_NAME;
import static org.assertj.core.api.Assertions.assertThat;


class TariffMapperTest {

    private static final TariffMapper TARIFF_MAPPER = Mappers.getMapper(TariffMapper.class);

    @BeforeAll
    static void init() {
        VatMapper vatMapper = Mappers.getMapper(VatMapper.class);
        VatRepository vatRepository = Mockito.mock(VatRepository.class);
        ReflectionTestUtils.setField(TARIFF_MAPPER, "vatMapper", vatMapper);
        ReflectionTestUtils.setField(TARIFF_MAPPER, "vatRepository", vatRepository);
    }

    @ParameterizedTest
    @MethodSource("providerDtos")
    void toEntity(TariffDtoIn tariffDtoIn, Long expectedLong) {
        Mockito
                .when(TARIFF_MAPPER.vatRepository.getById(tariffDtoIn.getVat()))
                .thenReturn(FK_VAT);

        assertThat(TARIFF_MAPPER.toEntity(tariffDtoIn))
                .returns(expectedLong, Tariff::getId)
                .returns(TEST_TARIFF_NAME, Tariff::getName)
                .returns(expectedLong, Tariff::getPrice);

        assertThat(TARIFF_MAPPER.toEntity(tariffDtoIn).getVat())
                .returns(LONG_POSITIVE, Vat::getId)
                .returns(TEST_VAT_NAME, Vat::getName)
                .returns(true, Vat::isDefault)
                .returns(FK_VAT_PERCENT, Vat::getPercent)
                .returns(FK_VAT_MULTIPLIER, Vat::getMultiplier);
    }

    @ParameterizedTest
    @MethodSource("providerEntities")
    void toDtoOut(Tariff tariff, Long expectedLong) {
        assertThat(TARIFF_MAPPER.toDtoOut(tariff))
                .returns(expectedLong, TariffDtoOut::getId)
                .returns(TEST_TARIFF_NAME, TariffDtoOut::getName)
                .returns(expectedLong, TariffDtoOut::getPrice)
                .returns(LONG_POSITIVE, TariffDtoOut::getVat);

        assertThat(TARIFF_MAPPER.toDtoOut(tariff).getVatDto())
                .returns(LONG_POSITIVE, VatDtoOut::getId)
                .returns(TEST_VAT_NAME, VatDtoOut::getName)
                .returns(true, VatDtoOut::isDefault)
                .returns(FK_VAT_PERCENT, VatDtoOut::getPercent)
                .returns(FK_VAT_MULTIPLIER, VatDtoOut::getMultiplier);
    }

    private static Stream<Arguments> providerEntities() {
        return Stream.of(
                Arguments.of(Tariff.builder().id(LONG_MIN).name(TEST_TARIFF_NAME).vat(FK_VAT).price(LONG_MIN).build(), LONG_MIN),
                Arguments.of(Tariff.builder().id(LONG_NEGATIVE).name(TEST_TARIFF_NAME).vat(FK_VAT).price(LONG_NEGATIVE).build(), LONG_NEGATIVE),
                Arguments.of(Tariff.builder().id(LONG_ZERO).name(TEST_TARIFF_NAME).vat(FK_VAT).price(LONG_ZERO).build(), LONG_ZERO),
                Arguments.of(Tariff.builder().id(LONG_POSITIVE).name(TEST_TARIFF_NAME).vat(FK_VAT).price(LONG_POSITIVE).build(), LONG_POSITIVE),
                Arguments.of(Tariff.builder().id(LONG_MAX).name(TEST_TARIFF_NAME).vat(FK_VAT).price(LONG_MAX).build(), LONG_MAX)
        );
    }

    private static Stream<Arguments> providerDtos() {
        return Stream.of(
                Arguments.of(TariffDtoIn.builder().id(LONG_MIN).name(TEST_TARIFF_NAME).vat(LONG_POSITIVE).price(LONG_MIN).build(), LONG_MIN),
                Arguments.of(TariffDtoIn.builder().id(LONG_NEGATIVE).name(TEST_TARIFF_NAME).vat(LONG_POSITIVE).price(LONG_NEGATIVE).build(), LONG_NEGATIVE),
                Arguments.of(TariffDtoIn.builder().id(LONG_ZERO).name(TEST_TARIFF_NAME).vat(LONG_POSITIVE).price(LONG_ZERO).build(), LONG_ZERO),
                Arguments.of(TariffDtoIn.builder().id(LONG_POSITIVE).name(TEST_TARIFF_NAME).vat(LONG_POSITIVE).price(LONG_POSITIVE).build(), LONG_POSITIVE),
                Arguments.of(TariffDtoIn.builder().id(LONG_MAX).name(TEST_TARIFF_NAME).vat(LONG_POSITIVE).price(LONG_MAX).build(), LONG_MAX)
        );
    }
}