package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.constant.TestEntities;
import cz.patyk.invoicesystem_be.dto.in.ServiceCatalogDtoIn;
import cz.patyk.invoicesystem_be.dto.out.ServiceCatalogDtoOut;
import cz.patyk.invoicesystem_be.entities.ServiceCatalog;
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


class ServiceCatalogMapperTest {
    private static final ServiceCatalogMapper SERVICE_CATALOG_MAPPER = Mappers.getMapper(ServiceCatalogMapper.class);

    @BeforeAll
    static void init() {
//        VatMapper vatMapper = Mappers.getMapper(VatMapper.class);
//        ReflectionTestUtils.setField(SERVICE_CATALOG_MAPPER, "vatMapper", vatMapper);
        VatService vatService = Mockito.mock(VatService.class);
        ReflectionTestUtils.setField(SERVICE_CATALOG_MAPPER, "vatService", vatService);
    }

    @ParameterizedTest
    @MethodSource("dtoInProvider")
    void toEntity(ServiceCatalogDtoIn serviceCatalogDtoIn) {
        Mockito
                .when(SERVICE_CATALOG_MAPPER.vatService.getEntityById(serviceCatalogDtoIn.getVat()))
                .thenReturn(TestEntities.VAT_ENTITY);

        assertThat(SERVICE_CATALOG_MAPPER.toEntity(serviceCatalogDtoIn))
                .returns(serviceCatalogDtoIn.getId(), ServiceCatalog::getId)
                .returns(serviceCatalogDtoIn.getName(), ServiceCatalog::getName)
                .returns(serviceCatalogDtoIn.getDescription(), ServiceCatalog::getDescription)
                .returns(serviceCatalogDtoIn.getPrice(), ServiceCatalog::getPrice)
                .returns(serviceCatalogDtoIn.getEstimateTimeDelivery(), ServiceCatalog::getEstimateTimeDelivery)
                .returns(serviceCatalogDtoIn.getEstimateTimeReaction(), ServiceCatalog::getEstimateTimeReaction)
        ;

        assertThat(SERVICE_CATALOG_MAPPER.toEntity(serviceCatalogDtoIn).getVat())
                .returns(TestEntities.VAT_ENTITY.getId(), Vat::getId)
                .returns(TestEntities.VAT_ENTITY.getName(), Vat::getName)
                .returns(TestEntities.VAT_ENTITY.isDefault(), Vat::isDefault)
                .returns(TestEntities.VAT_ENTITY.getPercent(), Vat::getPercent)
                .returns(TestEntities.VAT_ENTITY.getMultiplier(), Vat::getMultiplier)
        ;
    }

    @ParameterizedTest
    @MethodSource("entityProvider")
    void toDto(ServiceCatalog serviceCatalog) {
        assertThat(SERVICE_CATALOG_MAPPER.toDtoOut(serviceCatalog))
                .returns(serviceCatalog.getId(), ServiceCatalogDtoOut::getId)
                .returns(serviceCatalog.getName(), ServiceCatalogDtoOut::getName)
                .returns(serviceCatalog.getDescription(), ServiceCatalogDtoOut::getDescription)
                .returns(serviceCatalog.getPrice(), ServiceCatalogDtoOut::getPrice)
                .returns(serviceCatalog.getVat().getId(), ServiceCatalogDtoOut::getVat)
                .returns(serviceCatalog.getEstimateTimeDelivery(), ServiceCatalogDtoOut::getEstimateTimeDelivery)
                .returns(serviceCatalog.getEstimateTimeReaction(), ServiceCatalogDtoOut::getEstimateTimeReaction)
                .returns(serviceCatalog.isDisable(), ServiceCatalogDtoOut::isDisable)
        ;
    }

    private static Stream<Arguments> entityProvider() {
        return Stream.of(
                Arguments.of(ServiceCatalog.builder().id(Long.MIN_VALUE).vat(TestEntities.VAT_ENTITY).name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(false).build()),
                Arguments.of(ServiceCatalog.builder().id(NumberUtils.LONG_MINUS_ONE).vat(TestEntities.VAT_ENTITY).name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(false).build()),
                Arguments.of(ServiceCatalog.builder().id(NumberUtils.LONG_ZERO).vat(TestEntities.VAT_ENTITY).name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(false).build()),
                Arguments.of(ServiceCatalog.builder().id(NumberUtils.LONG_ONE).vat(TestEntities.VAT_ENTITY).name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(false).build()),
                Arguments.of(ServiceCatalog.builder().id(Long.MAX_VALUE).vat(TestEntities.VAT_ENTITY).name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(false).build())
        );
    }

    private static Stream<Arguments> dtoInProvider() {
        return Stream.of(
                Arguments.of(ServiceCatalogDtoIn.builder().id(Long.MIN_VALUE).vat(NumberUtils.LONG_ONE).name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(false).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().id(NumberUtils.LONG_MINUS_ONE).vat(NumberUtils.LONG_ONE).name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(false).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().id(NumberUtils.LONG_ZERO).vat(NumberUtils.LONG_ONE).name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(false).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().id(NumberUtils.LONG_ONE).vat(NumberUtils.LONG_ONE).name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(false).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().id(Long.MAX_VALUE).vat(NumberUtils.LONG_ONE).name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(false).build())
        );
    }
}