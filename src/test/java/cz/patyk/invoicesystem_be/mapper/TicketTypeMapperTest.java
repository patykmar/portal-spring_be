package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.TicketTypeDto;
import cz.patyk.invoicesystem_be.entities.TicketType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static cz.patyk.invoicesystem_be.common.TestConstants.TICKET_TYPE_TEST_NAME;
import static cz.patyk.invoicesystem_be.common.TestConstants.TICKET_TYPE_TEST_ABBREVIATION;
import static org.apache.commons.lang3.math.NumberUtils.FLOAT_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.FLOAT_ONE;
import static org.apache.commons.lang3.math.NumberUtils.FLOAT_ZERO;
import static org.apache.commons.lang3.math.NumberUtils.LONG_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;
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
                Arguments.of(TicketType.builder().id(Long.MIN_VALUE).name(TICKET_TYPE_TEST_NAME).abbreviation(TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(Float.MIN_VALUE).coefficientTime(Float.MIN_VALUE).build()),
                Arguments.of(TicketType.builder().id(LONG_MINUS_ONE).name(TICKET_TYPE_TEST_NAME).abbreviation(TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(FLOAT_MINUS_ONE).coefficientTime(FLOAT_MINUS_ONE).build()),
                Arguments.of(TicketType.builder().id(LONG_ZERO).name(TICKET_TYPE_TEST_NAME).abbreviation(TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(FLOAT_ZERO).coefficientTime(FLOAT_ZERO).build()),
                Arguments.of(TicketType.builder().id(LONG_ONE).name(TICKET_TYPE_TEST_NAME).abbreviation(TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(FLOAT_ONE).coefficientTime(FLOAT_ONE).build()),
                Arguments.of(TicketType.builder().id(Long.MAX_VALUE).name(TICKET_TYPE_TEST_NAME).abbreviation(TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(Float.MAX_VALUE).coefficientTime(Float.MAX_VALUE).build())
        );
    }

    private static Stream<Arguments> dtoProvider() {
        return Stream.of(
                Arguments.of(TicketTypeDto.builder().id(Long.MIN_VALUE).name(TICKET_TYPE_TEST_NAME).abbreviation(TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(Float.MIN_VALUE).coefficientTime(Float.MIN_VALUE).build()),
                Arguments.of(TicketTypeDto.builder().id(LONG_MINUS_ONE).name(TICKET_TYPE_TEST_NAME).abbreviation(TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(FLOAT_MINUS_ONE).coefficientTime(FLOAT_MINUS_ONE).build()),
                Arguments.of(TicketTypeDto.builder().id(LONG_ZERO).name(TICKET_TYPE_TEST_NAME).abbreviation(TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(FLOAT_ZERO).coefficientTime(FLOAT_ZERO).build()),
                Arguments.of(TicketTypeDto.builder().id(LONG_ONE).name(TICKET_TYPE_TEST_NAME).abbreviation(TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(FLOAT_ONE).coefficientTime(FLOAT_ONE).build()),
                Arguments.of(TicketTypeDto.builder().id(Long.MAX_VALUE).name(TICKET_TYPE_TEST_NAME).abbreviation(TICKET_TYPE_TEST_ABBREVIATION).isDisable(true).coefficientPrice(Float.MAX_VALUE).coefficientTime(Float.MAX_VALUE).build())
        );
    }
}