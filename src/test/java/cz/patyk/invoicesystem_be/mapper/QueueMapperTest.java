package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.dto.in.QueueDtoIn;
import cz.patyk.invoicesystem_be.dto.out.QueueDtoOut;
import cz.patyk.invoicesystem_be.entities.Queue;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class QueueMapperTest {
    private static final QueueMapper QUEUE_MAPPER = Mappers.getMapper(QueueMapper.class);

    @ParameterizedTest
    @MethodSource("providerDtoIn")
    void toEntity(QueueDtoIn queueDto) {
        assertThat(QUEUE_MAPPER.toEntity(queueDto))
                .returns(queueDto.getId(), Queue::getId)
                .returns(queueDto.getName(), Queue::getName);
    }

    @ParameterizedTest
    @MethodSource("providerEntities")
    void toDto(Queue queue) {
        assertThat(QUEUE_MAPPER.toDto(queue))
                .returns(queue.getId(), QueueDtoOut::getId)
                .returns(queue.getName(), QueueDtoOut::getName);
    }

    private static Stream<Arguments> providerDtoIn() {
        return Stream.of(
                Arguments.of(QueueDtoIn.builder().id(Long.MIN_VALUE).name(Common.QUEUE_TEST_NAME).build()),
                Arguments.of(QueueDtoIn.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.QUEUE_TEST_NAME).build()),
                Arguments.of(QueueDtoIn.builder().id(NumberUtils.LONG_ZERO).name(Common.QUEUE_TEST_NAME).build()),
                Arguments.of(QueueDtoIn.builder().id(NumberUtils.LONG_ONE).name(Common.QUEUE_TEST_NAME).build()),
                Arguments.of(QueueDtoIn.builder().id(Long.MAX_VALUE).name(Common.QUEUE_TEST_NAME).build())
        );
    }

    private static Stream<Arguments> providerEntities() {
        return Stream.of(
                Arguments.of(Queue.builder().id(Long.MIN_VALUE).name(Common.QUEUE_TEST_NAME).build()),
                Arguments.of(Queue.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.QUEUE_TEST_NAME).build()),
                Arguments.of(Queue.builder().id(NumberUtils.LONG_ZERO).name(Common.QUEUE_TEST_NAME).build()),
                Arguments.of(Queue.builder().id(NumberUtils.LONG_ONE).name(Common.QUEUE_TEST_NAME).build()),
                Arguments.of(Queue.builder().id(Long.MAX_VALUE).name(Common.QUEUE_TEST_NAME).build())
        );
    }
}