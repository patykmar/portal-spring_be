package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.dto.TicketTypeDto;
import cz.patyk.invoicesystem_be.entities.TicketType;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TicketTypeMapperTest {

    private static final TicketTypeMapper TICKET_TYPE_MAPPER = Mappers.getMapper(TicketTypeMapper.class);

    @ParameterizedTest
    @MethodSource("dtoProvider")
    void toEntity(TicketTypeDto ticketTypeDto) {
        assertThat(TICKET_TYPE_MAPPER.toEntity(ticketTypeDto))
                .returns(ticketTypeDto.getId(), TicketType::getId)
                .returns(ticketTypeDto.getName(), TicketType::getName)
                .returns(ticketTypeDto.getAbbreviation(), TicketType::getAbbreviation)
                .returns(ticketTypeDto.isDisable(), TicketType::isDisable)
                .returns(ticketTypeDto.getCoefficientPrice(), TicketType::getCoefficientPrice)
                .returns(ticketTypeDto.getCoefficientTime(), TicketType::getCoefficientTime);
    }

    @ParameterizedTest
    @MethodSource("entityProvider")
    void toDto(TicketType ticketType) {
        assertThat(TICKET_TYPE_MAPPER.toDto(ticketType))
                .returns(ticketType.getId(), TicketTypeDto::getId)
                .returns(ticketType.getName(), TicketTypeDto::getName)
                .returns(ticketType.getAbbreviation(), TicketTypeDto::getAbbreviation)
                .returns(ticketType.isDisable(), TicketTypeDto::isDisable)
                .returns(ticketType.getCoefficientPrice(), TicketTypeDto::getCoefficientPrice)
                .returns(ticketType.getCoefficientTime(), TicketTypeDto::getCoefficientTime);
    }

    private static Stream<Arguments> entityProvider() {
        return Stream.of(
                Arguments.of(TicketType.builder().id(Long.MIN_VALUE).name(Common.TICKET_TYPE_TEST_NAME).abbreviation(Common.TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(Float.MIN_VALUE).coefficientTime(Float.MIN_VALUE).build()),
                Arguments.of(TicketType.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.TICKET_TYPE_TEST_NAME).abbreviation(Common.TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(NumberUtils.FLOAT_MINUS_ONE).coefficientTime(NumberUtils.FLOAT_MINUS_ONE).build()),
                Arguments.of(TicketType.builder().id(NumberUtils.LONG_ZERO).name(Common.TICKET_TYPE_TEST_NAME).abbreviation(Common.TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(NumberUtils.FLOAT_ZERO).coefficientTime(NumberUtils.FLOAT_ZERO).build()),
                Arguments.of(TicketType.builder().id(NumberUtils.LONG_ONE).name(Common.TICKET_TYPE_TEST_NAME).abbreviation(Common.TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(NumberUtils.FLOAT_ONE).coefficientTime(NumberUtils.FLOAT_ONE).build()),
                Arguments.of(TicketType.builder().id(Long.MAX_VALUE).name(Common.TICKET_TYPE_TEST_NAME).abbreviation(Common.TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(Float.MAX_VALUE).coefficientTime(Float.MAX_VALUE).build())
        );
    }

    private static Stream<Arguments> dtoProvider() {
        return Stream.of(
                Arguments.of(TicketTypeDto.builder().id(Long.MIN_VALUE).name(Common.TICKET_TYPE_TEST_NAME).abbreviation(Common.TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(Float.MIN_VALUE).coefficientTime(Float.MIN_VALUE).build()),
                Arguments.of(TicketTypeDto.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.TICKET_TYPE_TEST_NAME).abbreviation(Common.TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(NumberUtils.FLOAT_MINUS_ONE).coefficientTime(NumberUtils.FLOAT_MINUS_ONE).build()),
                Arguments.of(TicketTypeDto.builder().id(NumberUtils.LONG_ZERO).name(Common.TICKET_TYPE_TEST_NAME).abbreviation(Common.TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(NumberUtils.FLOAT_ZERO).coefficientTime(NumberUtils.FLOAT_ZERO).build()),
                Arguments.of(TicketTypeDto.builder().id(NumberUtils.LONG_ONE).name(Common.TICKET_TYPE_TEST_NAME).abbreviation(Common.TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(NumberUtils.FLOAT_ONE).coefficientTime(NumberUtils.FLOAT_ONE).build()),
                Arguments.of(TicketTypeDto.builder().id(Long.MAX_VALUE).name(Common.TICKET_TYPE_TEST_NAME).abbreviation(Common.TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(Float.MAX_VALUE).coefficientTime(Float.MAX_VALUE).build())
        );
    }
}