package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.constant.TestDtos;
import cz.patyk.invoicesystem_be.constant.TestEntities;
import cz.patyk.invoicesystem_be.dto.out.CiDtoOut;
import cz.patyk.invoicesystem_be.entities.Ci;
import cz.patyk.invoicesystem_be.mapper.CiMapper;
import cz.patyk.invoicesystem_be.repositories.CiRepository;
import cz.patyk.invoicesystem_be.service.CiService;
import cz.patyk.invoicesystem_be.service.CompanyService;
import cz.patyk.invoicesystem_be.service.ErrorHandleService;
import cz.patyk.invoicesystem_be.service.GeneralStateService;
import cz.patyk.invoicesystem_be.service.QueueServices;
import cz.patyk.invoicesystem_be.service.TariffService;
import cz.patyk.invoicesystem_be.service.UserService;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

class CiControllerTest {
    private static final CiRepository CI_REPOSITORY = Mockito.mock(CiRepository.class);
    private static final CiMapper CI_MAPPER = Mappers.getMapper(CiMapper.class);
    private static final PageRequest PAGE_REQUEST = PageRequest.of(0, 10);
    private final ErrorHandleService errorHandleService = Mockito.mock(ErrorHandleService.class);
    private final CiService ciService = new CiService(CI_REPOSITORY, CI_MAPPER, errorHandleService);
    private final CiControllerBasic ciController = new CiControllerBasic(ciService);

    @BeforeAll
    static void setUp() {
        UserService userService = Mockito.mock(UserService.class);
        GeneralStateService generalStateService = Mockito.mock(GeneralStateService.class);
        TariffService tariffService = Mockito.mock(TariffService.class);
        CompanyService companyService = Mockito.mock(CompanyService.class);
        QueueServices queueServices = Mockito.mock(QueueServices.class);

        ReflectionTestUtils.setField(CI_MAPPER, "userService", userService);
        ReflectionTestUtils.setField(CI_MAPPER, "generalStateService", generalStateService);
        ReflectionTestUtils.setField(CI_MAPPER, "tariffService", tariffService);
        ReflectionTestUtils.setField(CI_MAPPER, "companyService", companyService);
        ReflectionTestUtils.setField(CI_MAPPER, "queueServices", queueServices);
        ReflectionTestUtils.setField(CI_MAPPER, "ciRepository", CI_REPOSITORY);

        Mockito
                .when(userService.getOneEntity(TestDtos.CI_DTO_IN.getCreatedUserId()))
                .thenReturn(TestEntities.USER);
        Mockito
                .when(generalStateService.getOneEntity(TestDtos.CI_DTO_IN.getStateId()))
                .thenReturn(TestEntities.GENERAL_STATE_01);
        Mockito
                .when(tariffService.getOneEntity(TestDtos.CI_DTO_IN.getTariffId()))
                .thenReturn(TestEntities.TARIFF_01);
        Mockito
                .when(companyService.getOneEntity(TestDtos.CI_DTO_IN.getCompanyId()))
                .thenReturn(TestEntities.COMPANY_01);
        Mockito
                .when(queueServices.getOneEntity(TestDtos.CI_DTO_IN.getQueueTier1()))
                .thenReturn(TestEntities.QUEUE_01);
        Mockito
                .when(queueServices.getOneEntity(TestDtos.CI_DTO_IN.getQueueTier2()))
                .thenReturn(TestEntities.QUEUE_02);
        Mockito
                .when(queueServices.getOneEntity(TestDtos.CI_DTO_IN.getQueueTier3()))
                .thenReturn(TestEntities.QUEUE_03);
    }

    @Test
    void getAllTest() {
        Mockito
                .when(CI_REPOSITORY.findAll(PAGE_REQUEST))
                .thenReturn(new PageImpl<>(TestEntities.CI_LIST));

        ResponseEntity<CollectionModel<CiDtoOut>> ciControllerAll = ciController.getAll(PAGE_REQUEST);

        assertThat(ciControllerAll)
                .returns(HttpStatus.OK, ResponseEntity::getStatusCode);

        assertThat(ciControllerAll.getHeaders())
                .isEmpty();

        assertThat(ciControllerAll.getBody())
                .isNotEmpty()
                .hasSize(3)
                .satisfies(ciDtoOuts -> assertThat(ciDtoOuts)
                        .first()
                        .hasNoNullFieldsOrPropertiesExcept("parentCiId")
                );
    }

    @Test
    void getOneTest() {
        Mockito.when(CI_REPOSITORY.findById(anyLong()))
                .thenReturn(Optional.of(TestEntities.CI_01));

        ResponseEntity<CiDtoOut> ciDtoOutResponseEntity = ciController.getOne(NumberUtils.LONG_ONE);

        assertThat(ciDtoOutResponseEntity)
                .returns(HttpStatus.OK, ResponseEntity::getStatusCode);

        assertThat(ciDtoOutResponseEntity.getHeaders())
                .isEmpty();

        assertThat(ciDtoOutResponseEntity.getBody())
                .hasNoNullFieldsOrPropertiesExcept("parentCiId");
    }

    @Test
    void newItemTest() {
        Mockito.when(CI_REPOSITORY.save(any(Ci.class)))
                .thenReturn(TestEntities.CI_01);

        ResponseEntity<CiDtoOut> ciDtoOutResponseEntity = ciController.newItem(TestDtos.CI_DTO_IN);

        assertThat(ciDtoOutResponseEntity)
                .returns(HttpStatus.OK, ResponseEntity::getStatusCode);

        assertThat(ciDtoOutResponseEntity.getHeaders())
                .isEmpty();

        assertThat(ciDtoOutResponseEntity.getBody())
                .hasNoNullFieldsOrPropertiesExcept("parentCiId");

    }

    @Test
    void editItemTest() {
        Mockito.when(CI_REPOSITORY.existsById(anyLong()))
                .thenReturn(true);
        Mockito.when(CI_REPOSITORY.save(any(Ci.class)))
                .thenReturn(TestEntities.CI_01);

        ResponseEntity<CiDtoOut> ciDtoOutResponseEntity = ciController.editItem(TestDtos.CI_DTO_IN, NumberUtils.LONG_ONE);

        assertThat(ciDtoOutResponseEntity)
                .returns(HttpStatus.OK, ResponseEntity::getStatusCode);

        assertThat(ciDtoOutResponseEntity.getHeaders())
                .isEmpty();

        assertThat(ciDtoOutResponseEntity.getBody())
                .hasNoNullFieldsOrPropertiesExcept("parentCiId");
    }

    @Test
    void deleteItemTest() {
        Mockito.when(CI_REPOSITORY.existsById(anyLong()))
                .thenReturn(true);

        ResponseEntity<Void> voidResponseEntity = ciController.deleteItem(NumberUtils.LONG_ONE);

        assertThat(voidResponseEntity)
                .returns(HttpStatus.NO_CONTENT, ResponseEntity::getStatusCode);

        assertThat(voidResponseEntity.getHeaders())
                .isEmpty();

        assertThat(voidResponseEntity.getBody())
                .isNull();
    }
}