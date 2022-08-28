package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.constant.TestDtos;
import cz.patyk.invoicesystem_be.constant.TestEntities;
import cz.patyk.invoicesystem_be.dto.out.SlaDtoOut;
import cz.patyk.invoicesystem_be.mapper.SlaMapper;
import cz.patyk.invoicesystem_be.repositories.SlaRepository;
import cz.patyk.invoicesystem_be.service.ErrorHandleService;
import cz.patyk.invoicesystem_be.service.InfluencingTicketService;
import cz.patyk.invoicesystem_be.service.SlaService;
import cz.patyk.invoicesystem_be.service.TariffService;
import cz.patyk.invoicesystem_be.service.TicketTypeService;
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

public class SlaControllerTest implements CrudControllerTest {
    private static final SlaMapper SLA_MAPPER = Mappers.getMapper(SlaMapper.class);
    private static final PageRequest PAGE_REQUEST = PageRequest.of(0, 10);
    private final SlaRepository slaRepository = Mockito.mock(SlaRepository.class);
    private final ErrorHandleService errorHandleService = Mockito.mock(ErrorHandleService.class);
    private final SlaService slaService = new SlaService(SLA_MAPPER, slaRepository, errorHandleService);
    private final SlaController slaController = new SlaController(slaService);

    @BeforeAll
    static void init() {
        TariffService tariffService = Mockito.mock(TariffService.class);
        InfluencingTicketService influencingTicketService = Mockito.mock(InfluencingTicketService.class);
        TicketTypeService ticketTypeService = Mockito.mock(TicketTypeService.class);

        ReflectionTestUtils.setField(SLA_MAPPER, "tariffService", tariffService);
        ReflectionTestUtils.setField(SLA_MAPPER, "influencingTicketService", influencingTicketService);
        ReflectionTestUtils.setField(SLA_MAPPER, "ticketTypeService", ticketTypeService);

        Mockito
                .when(tariffService.getOneEntity(TestDtos.SLA_DTO_IN.getTariffId()))
                .thenReturn(TestEntities.TARIFF_01);
        Mockito
                .when(influencingTicketService.getOneEntity(TestDtos.SLA_DTO_IN.getPriorityId()))
                .thenReturn(TestEntities.PRIORITY_01);
        Mockito
                .when(ticketTypeService.getOneEntity(TestDtos.SLA_DTO_IN.getTicketTypeId()))
                .thenReturn(TestEntities.TICKET_TYPE_01);
        Mockito
                .when(tariffService.getOneEntity(TestDtos.SLA_DTO_IN_EDIT.getTariffId()))
                .thenReturn(TestEntities.TARIFF_02);
        Mockito
                .when(influencingTicketService.getOneEntity(TestDtos.SLA_DTO_IN_EDIT.getPriorityId()))
                .thenReturn(TestEntities.PRIORITY_02);
        Mockito
                .when(ticketTypeService.getOneEntity(TestDtos.SLA_DTO_IN_EDIT.getTicketTypeId()))
                .thenReturn(TestEntities.TICKET_TYPE_02);
    }

    @Test
    @Override
    public void getAllTest() {
        Mockito
                .when(slaRepository.findAll(PAGE_REQUEST))
                .thenReturn(new PageImpl<>(TestEntities.SLA_LIST));

        ResponseEntity<CollectionModel<SlaDtoOut>> slasCollection = slaController.getAll(PAGE_REQUEST);

        assertThat(slasCollection)
                .returns(HttpStatus.OK, ResponseEntity::getStatusCode);

        assertThat(slasCollection.getHeaders())
                .isEmpty();

        assertThat(slasCollection.getBody())
                .isNotEmpty()
                .satisfies(slaDtoOuts -> assertThat(slaDtoOuts)
                        .first()
                        .returns(TestEntities.SLA_01.getId(), SlaDtoOut::getId)
                        .returns(TestEntities.SLA_01.getTariff().getId(), SlaDtoOut::getTariffId)
                        .returns(TestEntities.SLA_01.getPriority().getId(), SlaDtoOut::getPriorityId)
                        .returns(TestEntities.SLA_01.getTicketType().getId(), SlaDtoOut::getTicketTypeId)
                        .returns(TestEntities.SLA_01.getReactionTime(), SlaDtoOut::getReactionTime)
                        .returns(TestEntities.SLA_01.getResolvedTime(), SlaDtoOut::getResolvedTime)
                        .returns(TestEntities.SLA_01.getPriceMultiplier(), SlaDtoOut::getPriceMultiplier)
                );
    }

    @Test
    @Override
    public void getOneTest() {
        Mockito.when(slaRepository.findById(NumberUtils.LONG_ONE))
                .thenReturn(Optional.of(TestEntities.SLA_01));

        ResponseEntity<SlaDtoOut> slaControllerOne = slaController.getOne(NumberUtils.LONG_ONE);

        assertThat(slaControllerOne)
                .returns(HttpStatus.OK, ResponseEntity::getStatusCode);

        assertThat(slaControllerOne.getHeaders())
                .isEmpty();

        assertThat(slaControllerOne.getBody())
                .returns(NumberUtils.LONG_ONE, SlaDtoOut::getId)
                .returns(NumberUtils.LONG_ONE, SlaDtoOut::getTariffId)
                .returns(NumberUtils.LONG_ONE, SlaDtoOut::getPriorityId)
                .returns(NumberUtils.LONG_ONE, SlaDtoOut::getTicketTypeId)
                .returns(Common.SLA_REACTION_TIME, SlaDtoOut::getReactionTime)
                .returns(Common.SLA_RESOLVED_TIME, SlaDtoOut::getResolvedTime)
                .returns(Common.SLA_PRICE_MULTIPLIER, SlaDtoOut::getPriceMultiplier);
    }

    @Test
    @Override
    public void newItemTest() {
        Mockito.when(slaRepository.save(TestEntities.SLA_01))
                .thenReturn(TestEntities.SLA_01);

        ResponseEntity<SlaDtoOut> slaDtoOutResponseEntity = slaController.newItem(TestDtos.SLA_DTO_IN);

        assertThat(slaDtoOutResponseEntity)
                .returns(HttpStatus.OK, ResponseEntity::getStatusCode);

        assertThat(slaDtoOutResponseEntity.getHeaders())
                .isEmpty();

        assertThat(slaDtoOutResponseEntity.getBody())
                .returns(TestEntities.SLA_01.getId(), SlaDtoOut::getId)
                .returns(NumberUtils.LONG_ONE, SlaDtoOut::getTariffId)
                .returns(NumberUtils.LONG_ONE, SlaDtoOut::getPriorityId)
                .returns(NumberUtils.LONG_ONE, SlaDtoOut::getTicketTypeId)
                .returns(Common.SLA_REACTION_TIME, SlaDtoOut::getReactionTime)
                .returns(Common.SLA_RESOLVED_TIME, SlaDtoOut::getResolvedTime)
                .returns(Common.SLA_PRICE_MULTIPLIER, SlaDtoOut::getPriceMultiplier);
    }

    @Test
    @Override
    public void editItemTest() {
        Mockito
                .when(slaRepository.existsById(NumberUtils.LONG_ONE))
                .thenReturn(Boolean.TRUE);
        Mockito
                .when(slaRepository.save(TestEntities.SLA_01_EDIT))
                .thenReturn(TestEntities.SLA_01_EDIT);

        ResponseEntity<SlaDtoOut> slaDtoOutResponseEntity = slaController.editItem(
                TestDtos.SLA_DTO_IN_EDIT, NumberUtils.LONG_ONE
        );

        assertThat(slaDtoOutResponseEntity)
                .returns(HttpStatus.OK, ResponseEntity::getStatusCode);
        assertThat(slaDtoOutResponseEntity.getHeaders())
                .isEmpty();
        assertThat(slaDtoOutResponseEntity.getBody())
                .returns(NumberUtils.LONG_ONE, SlaDtoOut::getId)
                .returns(TestDtos.SLA_DTO_IN_EDIT.getPriorityId(), SlaDtoOut::getPriorityId)
                .returns(TestDtos.SLA_DTO_IN_EDIT.getTicketTypeId(), SlaDtoOut::getTicketTypeId)
                .returns(TestDtos.SLA_DTO_IN_EDIT.getReactionTime(), SlaDtoOut::getReactionTime)
                .returns(TestDtos.SLA_DTO_IN_EDIT.getResolvedTime(), SlaDtoOut::getResolvedTime)
                .returns(TestDtos.SLA_DTO_IN_EDIT.getPriceMultiplier(), SlaDtoOut::getPriceMultiplier);
    }

    @Test
    @Override
    public void deleteItemTest() {
        Mockito
                .when(slaRepository.existsById(NumberUtils.LONG_ONE))
                .thenReturn(true);

        ResponseEntity<Void> voidResponseEntity = slaController.deleteItem(NumberUtils.LONG_ONE);

        assertThat(voidResponseEntity)
                .returns(HttpStatus.NO_CONTENT, ResponseEntity::getStatusCode);
        assertThat(voidResponseEntity.getHeaders())
                .isEmpty();
        assertThat(voidResponseEntity.getBody())
                .isNull();
    }

}
