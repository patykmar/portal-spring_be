package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.InfluencingTicketDto;
import cz.patyk.invoicesystem_be.entities.InfluencingTicket;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static cz.patyk.invoicesystem_be.common.TestConstants.INFLUENCING_TICKET_TEST_NAME;
import static org.apache.commons.lang3.math.NumberUtils.FLOAT_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.FLOAT_ONE;
import static org.apache.commons.lang3.math.NumberUtils.FLOAT_ZERO;
import static org.apache.commons.lang3.math.NumberUtils.LONG_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;
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
                Arguments.of(InfluencingTicket.builder().id(Long.MIN_VALUE).name(INFLUENCING_TICKET_TEST_NAME).isForPriority(true).isForImpact(true).coefficientPrice(Float.MIN_VALUE).coefficientTime(Float.MIN_VALUE).build()),
                Arguments.of(InfluencingTicket.builder().id(LONG_MINUS_ONE).name(INFLUENCING_TICKET_TEST_NAME).isForPriority(true).isForImpact(false).coefficientPrice(FLOAT_MINUS_ONE).coefficientTime(FLOAT_MINUS_ONE).build()),
                Arguments.of(InfluencingTicket.builder().id(LONG_ZERO).name(INFLUENCING_TICKET_TEST_NAME).isForPriority(false).isForImpact(false).coefficientPrice(FLOAT_ZERO).coefficientTime(FLOAT_ZERO).build()),
                Arguments.of(InfluencingTicket.builder().id(LONG_ONE).name(INFLUENCING_TICKET_TEST_NAME).isForPriority(false).isForImpact(true).coefficientPrice(FLOAT_ONE).coefficientTime(FLOAT_ONE).build()),
                Arguments.of(InfluencingTicket.builder().id(Long.MAX_VALUE).name(INFLUENCING_TICKET_TEST_NAME).isForPriority(true).isForImpact(false).coefficientPrice(Float.MAX_VALUE).coefficientTime(Float.MAX_VALUE).build())
        );
    }

    private static Stream<Arguments> dtosProvider() {
        return Stream.of(
                Arguments.of(InfluencingTicketDto.builder().id(Long.MIN_VALUE).name(INFLUENCING_TICKET_TEST_NAME).isForPriority(true).isForImpact(true).coefficientPrice(Float.MIN_VALUE).coefficientTime(Float.MIN_VALUE).build()),
                Arguments.of(InfluencingTicketDto.builder().id(LONG_MINUS_ONE).name(INFLUENCING_TICKET_TEST_NAME).isForPriority(true).isForImpact(false).coefficientPrice(FLOAT_MINUS_ONE).coefficientTime(FLOAT_MINUS_ONE).build()),
                Arguments.of(InfluencingTicketDto.builder().id(LONG_ZERO).name(INFLUENCING_TICKET_TEST_NAME).isForPriority(false).isForImpact(false).coefficientPrice(FLOAT_ZERO).coefficientTime(FLOAT_ZERO).build()),
                Arguments.of(InfluencingTicketDto.builder().id(LONG_ONE).name(INFLUENCING_TICKET_TEST_NAME).isForPriority(false).isForImpact(true).coefficientPrice(FLOAT_ONE).coefficientTime(FLOAT_ONE).build()),
                Arguments.of(InfluencingTicketDto.builder().id(Long.MAX_VALUE).name(INFLUENCING_TICKET_TEST_NAME).isForPriority(true).isForImpact(false).coefficientPrice(Float.MAX_VALUE).coefficientTime(Float.MAX_VALUE).build())
        );
    }
}