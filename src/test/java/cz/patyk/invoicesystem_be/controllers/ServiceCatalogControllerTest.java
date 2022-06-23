package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.constant.TestEntities;
import cz.patyk.invoicesystem_be.dto.out.ServiceCatalogDtoOut;
import cz.patyk.invoicesystem_be.entities.ServiceCatalog;
import cz.patyk.invoicesystem_be.mapper.ServiceCatalogMapper;
import cz.patyk.invoicesystem_be.repositories.ServiceCatalogRepository;
import cz.patyk.invoicesystem_be.service.ErrorHandleService;
import cz.patyk.invoicesystem_be.service.ServiceCatalogService;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceCatalogControllerTest {
    private ServiceCatalogController serviceCatalogController;
    private ServiceCatalogRepository serviceCatalogRepository;
    private final static PageRequest pageRequest = PageRequest.of(0, 10);

    @BeforeEach
    void init() {
        serviceCatalogRepository = Mockito.mock(ServiceCatalogRepository.class);
        ServiceCatalogMapper serviceCatalogMapper = Mappers.getMapper(ServiceCatalogMapper.class);
        ErrorHandleService errorHandleService = new ErrorHandleService();
        ServiceCatalogService serviceCatalogService = new ServiceCatalogService(serviceCatalogRepository, serviceCatalogMapper, errorHandleService);
        serviceCatalogController = new ServiceCatalogController(serviceCatalogService);
    }

    @Test
    void getAll() {
        Page<ServiceCatalog> serviceCatalogsResponse = new PageImpl<>(TestEntities.SERVICE_CATALOG_LIST);

        Mockito
                .when(serviceCatalogRepository.findAll(pageRequest))
                .thenReturn(serviceCatalogsResponse);

        ResponseEntity<CollectionModel<ServiceCatalogDtoOut>> serviceCatalogControllerAll =
                serviceCatalogController.getAll(pageRequest);

        assertThat(serviceCatalogControllerAll)
                .returns(HttpStatus.OK, ResponseEntity::getStatusCode);

        assertThat(serviceCatalogControllerAll.getHeaders())
                .isEmpty();

        assertThat(serviceCatalogControllerAll.getBody())
                .isNotEmpty();

        assertThat(serviceCatalogControllerAll.getBody().getContent())
                .isNotEmpty()
                .satisfies(serviceCatalogDtoOut -> assertThat(serviceCatalogDtoOut)
                        .first()
                        .returns(TestEntities.SERVICE_CATALOG_1.getName(), ServiceCatalogDtoOut::getName)
                        .returns(TestEntities.SERVICE_CATALOG_1.getDescription(), ServiceCatalogDtoOut::getDescription)
                        .returns(TestEntities.SERVICE_CATALOG_1.getPrice(), ServiceCatalogDtoOut::getPrice)
                        .returns(TestEntities.SERVICE_CATALOG_1.getVat(), ServiceCatalogDtoOut::getVat)
                        .returns(TestEntities.SERVICE_CATALOG_1.getEstimateTimeDelivery(), ServiceCatalogDtoOut::getEstimateTimeDelivery)
                        .returns(TestEntities.SERVICE_CATALOG_1.getEstimateTimeReaction(), ServiceCatalogDtoOut::getEstimateTimeReaction)
                )
        ;
    }

    @Test
    void getOne() {
        Mockito
                .when(serviceCatalogRepository.findById(NumberUtils.LONG_ONE))
                .thenReturn(Optional.of(TestEntities.SERVICE_CATALOG_1))
        ;

        ResponseEntity<ServiceCatalogDtoOut> serviceCatalogDtoOutResponse = serviceCatalogController.getOne(NumberUtils.LONG_ONE);

        assertThat(serviceCatalogDtoOutResponse)
                .returns(HttpStatus.OK, ResponseEntity::getStatusCode);

        assertThat(serviceCatalogDtoOutResponse.getHeaders())
                .isEmpty();

        assertThat(serviceCatalogDtoOutResponse.getBody())
                .returns(TestEntities.SERVICE_CATALOG_1.getId(), ServiceCatalogDtoOut::getId)
                .returns(TestEntities.SERVICE_CATALOG_1.getName(), ServiceCatalogDtoOut::getName)
                .returns(TestEntities.SERVICE_CATALOG_1.getDescription(), ServiceCatalogDtoOut::getDescription)
                .returns(TestEntities.SERVICE_CATALOG_1.getPrice(), ServiceCatalogDtoOut::getPrice)
                .returns(TestEntities.SERVICE_CATALOG_1.getVat(), ServiceCatalogDtoOut::getVat)
                .returns(TestEntities.SERVICE_CATALOG_1.getEstimateTimeDelivery(), ServiceCatalogDtoOut::getEstimateTimeDelivery)
                .returns(TestEntities.SERVICE_CATALOG_1.getEstimateTimeReaction(), ServiceCatalogDtoOut::getEstimateTimeReaction)
                .returns(TestEntities.SERVICE_CATALOG_1.isDisable(), ServiceCatalogDtoOut::isDisable)
        ;


    }

    @Test
    void editItem() {
    }

    @Test
    void deleteItem() {
    }
}