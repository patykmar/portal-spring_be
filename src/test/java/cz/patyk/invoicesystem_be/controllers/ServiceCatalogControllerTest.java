package cz.patyk.invoicesystem_be.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.constant.TestDtos;
import cz.patyk.invoicesystem_be.constant.TestEntities;
import cz.patyk.invoicesystem_be.dto.in.ServiceCatalogDtoIn;
import cz.patyk.invoicesystem_be.dto.out.ServiceCatalogDtoOut;
import cz.patyk.invoicesystem_be.entities.ServiceCatalog;
import cz.patyk.invoicesystem_be.mapper.ServiceCatalogMapper;
import cz.patyk.invoicesystem_be.repositories.ServiceCatalogRepository;
import cz.patyk.invoicesystem_be.service.ErrorHandleService;
import cz.patyk.invoicesystem_be.service.ServiceCatalogService;
import cz.patyk.invoicesystem_be.service.VatService;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ServiceCatalogControllerTest {
    private final ServiceCatalogRepository serviceCatalogRepository = Mockito.mock(ServiceCatalogRepository.class);
    private final ServiceCatalogMapper serviceCatalogMapper = Mappers.getMapper(ServiceCatalogMapper.class);
    private final VatService vatService = Mockito.mock(VatService.class);
    private final static PageRequest pageRequest = PageRequest.of(0, 10);
    private ServiceCatalogController serviceCatalogController;

    private static final String URL = "/service-catalog";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(serviceCatalogMapper, "vatService", vatService);
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
                        .returns(TestEntities.SERVICE_CATALOG_1.getVat().getId(), ServiceCatalogDtoOut::getVat)
                        .returns(TestEntities.SERVICE_CATALOG_1.getEstimateTimeDelivery(), ServiceCatalogDtoOut::getEstimateTimeDelivery)
                        .returns(TestEntities.SERVICE_CATALOG_1.getEstimateTimeReaction(), ServiceCatalogDtoOut::getEstimateTimeReaction)
                );
    }

    @Test
    void getOne() {
        Mockito
                .when(serviceCatalogRepository.findById(NumberUtils.LONG_ONE))
                .thenReturn(Optional.of(TestEntities.SERVICE_CATALOG_1));

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
                .returns(TestEntities.SERVICE_CATALOG_1.getVat().getId(), ServiceCatalogDtoOut::getVat)
                .returns(TestEntities.SERVICE_CATALOG_1.getEstimateTimeDelivery(), ServiceCatalogDtoOut::getEstimateTimeDelivery)
                .returns(TestEntities.SERVICE_CATALOG_1.getEstimateTimeReaction(), ServiceCatalogDtoOut::getEstimateTimeReaction)
                .returns(TestEntities.SERVICE_CATALOG_1.isDisable(), ServiceCatalogDtoOut::isDisable)
        ;
    }

    @Test
    void editItem() {
        ServiceCatalog serviceCatalogEdited = TestEntities.SERVICE_CATALOG_2;
        serviceCatalogEdited.setId(NumberUtils.LONG_ONE);

        Mockito.when(serviceCatalogRepository.findById(NumberUtils.LONG_ONE))
                .thenReturn(Optional.of(TestEntities.SERVICE_CATALOG_1));
        Mockito.when(serviceCatalogRepository.existsById(NumberUtils.LONG_ONE))
                .thenReturn(true);
        Mockito.when(vatService.getEntityById(2L))
                .thenReturn(TestEntities.VAT_ENTITY_2);
        Mockito.when(serviceCatalogRepository.save(serviceCatalogEdited))
                .thenReturn(serviceCatalogEdited);

        ResponseEntity<ServiceCatalogDtoOut> serviceCatalogDtoOutResponse = serviceCatalogController.editItem(
                TestDtos.SERVICE_CATALOG_DTO_IN, NumberUtils.LONG_ONE
        );

        assertThat(serviceCatalogDtoOutResponse)
                .returns(HttpStatus.OK, ResponseEntity::getStatusCode);

        assertThat(serviceCatalogDtoOutResponse.getHeaders())
                .isEmpty();

        assertThat(serviceCatalogDtoOutResponse.getBody())
                .returns(serviceCatalogEdited.getId(), ServiceCatalogDtoOut::getId)
                .returns(serviceCatalogEdited.getName(), ServiceCatalogDtoOut::getName)
                .returns(serviceCatalogEdited.getDescription(), ServiceCatalogDtoOut::getDescription)
                .returns(serviceCatalogEdited.getPrice(), ServiceCatalogDtoOut::getPrice)
                .returns(serviceCatalogEdited.getVat().getId(), ServiceCatalogDtoOut::getVat)
                .returns(serviceCatalogEdited.getEstimateTimeDelivery(), ServiceCatalogDtoOut::getEstimateTimeDelivery)
                .returns(serviceCatalogEdited.getEstimateTimeReaction(), ServiceCatalogDtoOut::getEstimateTimeReaction)
                .returns(serviceCatalogEdited.isDisable(), ServiceCatalogDtoOut::isDisable);
    }

    @ParameterizedTest
    @MethodSource("provideWrongEditParameters")
    void editItemValidDto(ServiceCatalogDtoIn serviceCatalogDtoIn) throws Exception {
        String serviceCatalogDtoInAsJson = objectMapper.writeValueAsString(serviceCatalogDtoIn);

        mockMvc.perform(MockMvcRequestBuilders.put(URL + "/" + NumberUtils.LONG_ONE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serviceCatalogDtoInAsJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        ;
    }

    private static Stream<Arguments> provideWrongEditParameters() {
        return Stream.of(
                Arguments.of(ServiceCatalogDtoIn.builder().name(null).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).vat(NumberUtils.LONG_ONE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(true).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().name("").description(null).price(Common.SERVICE_CATALOG_PRICE).vat(NumberUtils.LONG_ONE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(true).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().name(Common.SERVICE_CATALOG_NAME).description(null).price(Common.SERVICE_CATALOG_PRICE).vat(NumberUtils.LONG_ONE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(true).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().name(Common.SERVICE_CATALOG_NAME).description("").price(NumberUtils.LONG_MINUS_ONE).vat(NumberUtils.LONG_ONE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(true).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(NumberUtils.LONG_MINUS_ONE).vat(NumberUtils.LONG_ONE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(true).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(null).vat(NumberUtils.LONG_ONE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(true).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).vat(NumberUtils.LONG_MINUS_ONE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(true).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).vat(null).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(true).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).vat(NumberUtils.LONG_ONE).estimateTimeDelivery(NumberUtils.INTEGER_ZERO).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(true).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).vat(NumberUtils.LONG_ONE).estimateTimeDelivery(NumberUtils.INTEGER_MINUS_ONE).estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).isDisable(true).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).vat(NumberUtils.LONG_ONE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(NumberUtils.INTEGER_ZERO).isDisable(true).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION).price(Common.SERVICE_CATALOG_PRICE).vat(NumberUtils.LONG_ONE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY).estimateTimeReaction(NumberUtils.INTEGER_MINUS_ONE).isDisable(true).build()),
                Arguments.of(ServiceCatalogDtoIn.builder().name(null).description(null).price(null).vat(null).estimateTimeDelivery(NumberUtils.INTEGER_MINUS_ONE).estimateTimeReaction(NumberUtils.INTEGER_MINUS_ONE).isDisable(true).build())
        );
    }

    @Test
    void deleteItem() {
        Mockito.when(serviceCatalogRepository.existsById(NumberUtils.LONG_ONE))
                .thenReturn(true);
        ResponseEntity<Void> voidResponseEntity = serviceCatalogController.deleteItem(NumberUtils.LONG_ONE);

        assertThat(voidResponseEntity)
                .returns(HttpStatus.NO_CONTENT, ResponseEntity::getStatusCode);
        assertThat(voidResponseEntity.getHeaders())
                .isEmpty();
        assertThat(voidResponseEntity.getBody())
                .isNull();
    }
}