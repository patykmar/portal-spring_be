package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.constant.TestEntities;
import cz.patyk.invoicesystem_be.dto.in.SlaDtoIn;
import cz.patyk.invoicesystem_be.dto.out.SlaDtoOut;
import cz.patyk.invoicesystem_be.entities.Sla;
import cz.patyk.invoicesystem_be.entities.Tariff;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SlaMapperTest {
    private static final SlaMapper SLA_MAPPER = Mappers.getMapper(SlaMapper.class);

    @BeforeAll
    static void init() {
        TicketTypeMapper ticketTypeMapper = Mappers.getMapper(TicketTypeMapper.class);
        TariffMapper tariffMapper = Mappers.getMapper(TariffMapper.class);
        InfluencingTicketMapper influencingTicketMapper = Mappers.getMapper(InfluencingTicketMapper.class);

//        ReflectionTestUtils.setField(SLA_MAPPER, "ticketTypeMapper", ticketTypeMapper);
        ReflectionTestUtils.setField(SLA_MAPPER, "tariffMapper", tariffMapper);
//        ReflectionTestUtils.setField(SLA_MAPPER, "influencingTicketMapper", influencingTicketMapper);
    }

    @ParameterizedTest
    @MethodSource("dtoProviders")
    void toEntity(SlaDtoIn slaDtoIn) {
        Mockito
                .when(SLA_MAPPER.tariffService.getOneEntity(slaDtoIn.getTariffId()))
                .thenReturn(TestEntities.TARIFF);
        Mockito
                .when(SLA_MAPPER.influencingTicketService.getOneEntity(slaDtoIn.getPriorityId()))
                .thenReturn(TestEntities.PRIORITY);
        Mockito
                .when(SLA_MAPPER.ticketTypeService.getOneEntity(slaDtoIn.getTicketTypeId()))
                .thenReturn(TestEntities.TICKET_TYPE);


        assertThat(SLA_MAPPER.toEntity(slaDtoIn))
                .returns(slaDtoIn.getId(), Sla::getId)
                .returns(slaDtoIn.getReactionTime(), Sla::getReactionTime)
                .returns(slaDtoIn.getResolvedTime(), Sla::getResolvedTime)
                .returns(slaDtoIn.getPriceMultiplier(), Sla::getPriceMultiplier);

        //TODO: fix the rest of test
        assertThat(SLA_MAPPER.toEntity(slaDtoIn).getTariff())
                .returns(NumberUtils.LONG_ONE, Tariff::getId)
                .returns(Common.TARIFF_TEST_NAME, Tariff::getName);
    }

    @ParameterizedTest
    @MethodSource("entityProvider")
    void toDtoOut(Sla sla) {
        assertThat(SLA_MAPPER.toDtoOut(sla))
                .returns(sla.getId(), SlaDtoOut::getId)
                .returns(sla.getTariff().getId(), SlaDtoOut::getTariffId)
                .returns(sla.getTicketType().getId(), SlaDtoOut::getTicketTypeId)
                .returns(sla.getPriority().getId(), SlaDtoOut::getPriorityId)
                .returns(sla.getReactionTime(), SlaDtoOut::getReactionTime)
                .returns(sla.getResolvedTime(), SlaDtoOut::getResolvedTime)
                .returns(sla.getPriceMultiplier(), SlaDtoOut::getPriceMultiplier);
    }

    private static Stream<Arguments> entityProvider() {
        return Stream.of(
                Arguments.of(Sla.builder().id(Long.MIN_VALUE).tariff(TestEntities.TARIFF).ticketType(TestEntities.TICKET_TYPE).priority(TestEntities.PRIORITY).reactionTime(4321).resolvedTime(12345).priceMultiplier(150).build()),
                Arguments.of(Sla.builder().id(NumberUtils.LONG_MINUS_ONE).tariff(TestEntities.TARIFF).ticketType(TestEntities.TICKET_TYPE).priority(TestEntities.PRIORITY).reactionTime(4321).resolvedTime(12345).priceMultiplier(150).build()),
                Arguments.of(Sla.builder().id(NumberUtils.LONG_ZERO).tariff(TestEntities.TARIFF).ticketType(TestEntities.TICKET_TYPE).priority(TestEntities.PRIORITY).reactionTime(4321).resolvedTime(12345).priceMultiplier(150).build()),
                Arguments.of(Sla.builder().id(NumberUtils.LONG_ONE).tariff(TestEntities.TARIFF).ticketType(TestEntities.TICKET_TYPE).priority(TestEntities.PRIORITY).reactionTime(4321).resolvedTime(12345).priceMultiplier(150).build()),
                Arguments.of(Sla.builder().id(Long.MAX_VALUE).tariff(TestEntities.TARIFF).ticketType(TestEntities.TICKET_TYPE).priority(TestEntities.PRIORITY).reactionTime(4321).resolvedTime(12345).priceMultiplier(150).build())
        );
    }

    private static Stream<Arguments> dtoProviders() {
        return Stream.of(
                Arguments.of(SlaDtoIn.builder().id(Long.MIN_VALUE).tariffId(NumberUtils.LONG_ONE).ticketTypeId(NumberUtils.LONG_ONE).priorityId(NumberUtils.LONG_ONE).reactionTime(4321).resolvedTime(12345).priceMultiplier(150).build()),
                Arguments.of(SlaDtoIn.builder().id(NumberUtils.LONG_MINUS_ONE).tariffId(NumberUtils.LONG_ONE).ticketTypeId(NumberUtils.LONG_ONE).priorityId(NumberUtils.LONG_ONE).reactionTime(4321).resolvedTime(12345).priceMultiplier(150).build()),
                Arguments.of(SlaDtoIn.builder().id(NumberUtils.LONG_ZERO).tariffId(NumberUtils.LONG_ONE).ticketTypeId(NumberUtils.LONG_ONE).priorityId(NumberUtils.LONG_ONE).reactionTime(4321).resolvedTime(12345).priceMultiplier(150).build()),
                Arguments.of(SlaDtoIn.builder().id(NumberUtils.LONG_ONE).tariffId(NumberUtils.LONG_ONE).ticketTypeId(NumberUtils.LONG_ONE).priorityId(NumberUtils.LONG_ONE).reactionTime(4321).resolvedTime(12345).priceMultiplier(150).build()),
                Arguments.of(SlaDtoIn.builder().id(Long.MAX_VALUE).tariffId(NumberUtils.LONG_ONE).ticketTypeId(NumberUtils.LONG_ONE).priorityId(NumberUtils.LONG_ONE).reactionTime(4321).resolvedTime(12345).priceMultiplier(150).build())
        );
    }
}