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

import static cz.patyk.invoicesystem_be.common.TestConstants.VAT_TEST_ENTITY;
import static cz.patyk.invoicesystem_be.common.TestConstants.VAT_TEST_MULTIPLIER;
import static cz.patyk.invoicesystem_be.common.TestConstants.VAT_TEST_PERCENT;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_NEGATIVE;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_POSITIVE;
import static cz.patyk.invoicesystem_be.common.TestConstants.LONG_ZERO;
import static cz.patyk.invoicesystem_be.common.TestConstants.TARIFF_TEST_NAME;
import static cz.patyk.invoicesystem_be.common.TestConstants.VAT_TEST_NAME;
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
                .thenReturn(VAT_TEST_ENTITY);

        assertThat(TARIFF_MAPPER.toEntity(tariffDtoIn))
                .returns(expectedLong, Tariff::getId)
                .returns(TARIFF_TEST_NAME, Tariff::getName)
                .returns(expectedLong, Tariff::getPrice);

        assertThat(TARIFF_MAPPER.toEntity(tariffDtoIn).getVat())
                .returns(LONG_POSITIVE, Vat::getId)
                .returns(VAT_TEST_NAME, Vat::getName)
                .returns(true, Vat::isDefault)
                .returns(VAT_TEST_PERCENT, Vat::getPercent)
                .returns(VAT_TEST_MULTIPLIER, Vat::getMultiplier);
    }

    @ParameterizedTest
    @MethodSource("providerEntities")
    void toDtoOut(Tariff tariff, Long expectedLong) {
        assertThat(TARIFF_MAPPER.toDtoOut(tariff))
                .returns(expectedLong, TariffDtoOut::getId)
                .returns(TARIFF_TEST_NAME, TariffDtoOut::getName)
                .returns(expectedLong, TariffDtoOut::getPrice)
                .returns(LONG_POSITIVE, TariffDtoOut::getVat);

        assertThat(TARIFF_MAPPER.toDtoOut(tariff).getVatDto())
                .returns(LONG_POSITIVE, VatDtoOut::getId)
                .returns(VAT_TEST_NAME, VatDtoOut::getName)
                .returns(true, VatDtoOut::isDefault)
                .returns(VAT_TEST_PERCENT, VatDtoOut::getPercent)
                .returns(VAT_TEST_MULTIPLIER, VatDtoOut::getMultiplier);
    }

    private static Stream<Arguments> providerEntities() {
        return Stream.of(
                Arguments.of(Tariff.builder().id(Long.MIN_VALUE).name(TARIFF_TEST_NAME).vat(VAT_TEST_ENTITY).price(Long.MIN_VALUE).build(), Long.MIN_VALUE),
                Arguments.of(Tariff.builder().id(LONG_NEGATIVE).name(TARIFF_TEST_NAME).vat(VAT_TEST_ENTITY).price(LONG_NEGATIVE).build(), LONG_NEGATIVE),
                Arguments.of(Tariff.builder().id(LONG_ZERO).name(TARIFF_TEST_NAME).vat(VAT_TEST_ENTITY).price(LONG_ZERO).build(), LONG_ZERO),
                Arguments.of(Tariff.builder().id(LONG_POSITIVE).name(TARIFF_TEST_NAME).vat(VAT_TEST_ENTITY).price(LONG_POSITIVE).build(), LONG_POSITIVE),
                Arguments.of(Tariff.builder().id(Long.MAX_VALUE).name(TARIFF_TEST_NAME).vat(VAT_TEST_ENTITY).price(Long.MAX_VALUE).build(), Long.MAX_VALUE)
        );
    }

    private static Stream<Arguments> providerDtos() {
        return Stream.of(
                Arguments.of(TariffDtoIn.builder().id(Long.MIN_VALUE).name(TARIFF_TEST_NAME).vat(LONG_POSITIVE).price(Long.MIN_VALUE).build(), Long.MIN_VALUE),
                Arguments.of(TariffDtoIn.builder().id(LONG_NEGATIVE).name(TARIFF_TEST_NAME).vat(LONG_POSITIVE).price(LONG_NEGATIVE).build(), LONG_NEGATIVE),
                Arguments.of(TariffDtoIn.builder().id(LONG_ZERO).name(TARIFF_TEST_NAME).vat(LONG_POSITIVE).price(LONG_ZERO).build(), LONG_ZERO),
                Arguments.of(TariffDtoIn.builder().id(LONG_POSITIVE).name(TARIFF_TEST_NAME).vat(LONG_POSITIVE).price(LONG_POSITIVE).build(), LONG_POSITIVE),
                Arguments.of(TariffDtoIn.builder().id(Long.MAX_VALUE).name(TARIFF_TEST_NAME).vat(LONG_POSITIVE).price(Long.MAX_VALUE).build(), Long.MAX_VALUE)
        );
    }
}