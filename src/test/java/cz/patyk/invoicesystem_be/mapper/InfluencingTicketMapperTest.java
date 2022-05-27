package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.dto.InfluencingTicketDto;
import cz.patyk.invoicesystem_be.entities.InfluencingTicket;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class InfluencingTicketMapperTest {
    private static final InfluencingTicketMapper INFLUENCING_TICKET_MAPPER = Mappers.getMapper(InfluencingTicketMapper.class);

    @ParameterizedTest
    @MethodSource("entityProvider")
    void toDto(InfluencingTicket influencingTicket) {
        assertThat(INFLUENCING_TICKET_MAPPER.toDto(influencingTicket))
                .returns(influencingTicket.getId(), InfluencingTicketDto::getId)
                .returns(influencingTicket.getName(), InfluencingTicketDto::getName)
                .returns(influencingTicket.isForPriority(), InfluencingTicketDto::isForPriority)
                .returns(influencingTicket.isForImpact(), InfluencingTicketDto::isForImpact)
                .returns(influencingTicket.getCoefficientPrice(), InfluencingTicketDto::getCoefficientPrice)
                .returns(influencingTicket.getCoefficientTime(), InfluencingTicketDto::getCoefficientTime);
    }

    @ParameterizedTest
    @MethodSource("dtosProvider")
    void toEntity(InfluencingTicketDto influencingTicketDto) {
        assertThat(INFLUENCING_TICKET_MAPPER.toEntity(influencingTicketDto))
                .returns(influencingTicketDto.getId(), InfluencingTicket::getId)
                .returns(influencingTicketDto.getName(), InfluencingTicket::getName)
                .returns(influencingTicketDto.isForPriority(), InfluencingTicket::isForPriority)
                .returns(influencingTicketDto.isForImpact(), InfluencingTicket::isForImpact)
                .returns(influencingTicketDto.getCoefficientPrice(), InfluencingTicket::getCoefficientPrice)
                .returns(influencingTicketDto.getCoefficientTime(), InfluencingTicket::getCoefficientTime);
    }

    private static Stream<Arguments> entityProvider() {
        return Stream.of(
                Arguments.of(InfluencingTicket.builder().id(Long.MIN_VALUE).name(Common.INFLUENCING_TICKET_TEST_NAME).isForPriority(true).isForImpact(true).coefficientPrice(Float.MIN_VALUE).coefficientTime(Float.MIN_VALUE).build()),
                Arguments.of(InfluencingTicket.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.INFLUENCING_TICKET_TEST_NAME).isForPriority(true).isForImpact(false).coefficientPrice(NumberUtils.FLOAT_MINUS_ONE).coefficientTime(NumberUtils.FLOAT_MINUS_ONE).build()),
                Arguments.of(InfluencingTicket.builder().id(NumberUtils.LONG_ZERO).name(Common.INFLUENCING_TICKET_TEST_NAME).isForPriority(false).isForImpact(false).coefficientPrice(NumberUtils.FLOAT_ZERO).coefficientTime(NumberUtils.FLOAT_ZERO).build()),
                Arguments.of(InfluencingTicket.builder().id(NumberUtils.LONG_ONE).name(Common.INFLUENCING_TICKET_TEST_NAME).isForPriority(false).isForImpact(true).coefficientPrice(NumberUtils.FLOAT_ONE).coefficientTime(NumberUtils.FLOAT_ONE).build()),
                Arguments.of(InfluencingTicket.builder().id(Long.MAX_VALUE).name(Common.INFLUENCING_TICKET_TEST_NAME).isForPriority(true).isForImpact(false).coefficientPrice(Float.MAX_VALUE).coefficientTime(Float.MAX_VALUE).build())
        );
    }

    private static Stream<Arguments> dtosProvider() {
        return Stream.of(
                Arguments.of(InfluencingTicketDto.builder().id(Long.MIN_VALUE).name(Common.INFLUENCING_TICKET_TEST_NAME).isForPriority(true).isForImpact(true).coefficientPrice(Float.MIN_VALUE).coefficientTime(Float.MIN_VALUE).build()),
                Arguments.of(InfluencingTicketDto.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.INFLUENCING_TICKET_TEST_NAME).isForPriority(true).isForImpact(false).coefficientPrice(NumberUtils.FLOAT_MINUS_ONE).coefficientTime(NumberUtils.FLOAT_MINUS_ONE).build()),
                Arguments.of(InfluencingTicketDto.builder().id(NumberUtils.LONG_ZERO).name(Common.INFLUENCING_TICKET_TEST_NAME).isForPriority(false).isForImpact(false).coefficientPrice(NumberUtils.FLOAT_ZERO).coefficientTime(NumberUtils.FLOAT_ZERO).build()),
                Arguments.of(InfluencingTicketDto.builder().id(NumberUtils.LONG_ONE).name(Common.INFLUENCING_TICKET_TEST_NAME).isForPriority(false).isForImpact(true).coefficientPrice(NumberUtils.FLOAT_ONE).coefficientTime(NumberUtils.FLOAT_ONE).build()),
                Arguments.of(InfluencingTicketDto.builder().id(Long.MAX_VALUE).name(Common.INFLUENCING_TICKET_TEST_NAME).isForPriority(true).isForImpact(false).coefficientPrice(Float.MAX_VALUE).coefficientTime(Float.MAX_VALUE).build())
        );
    }
}