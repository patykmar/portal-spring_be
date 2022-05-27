package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.dto.GeneralStateDto;
import cz.patyk.invoicesystem_be.entities.GeneralState;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GeneralStateMapperTest {
    private static final GeneralStateMapper GENERAL_STATE_MAPPER = Mappers.getMapper(GeneralStateMapper.class);

    @ParameterizedTest
    @MethodSource("dtosProvider")
    void toEntity(GeneralStateDto generalStateDto) {
        assertThat(GENERAL_STATE_MAPPER.toEntity(generalStateDto))
                .returns(generalStateDto.getId(), GeneralState::getId)
                .returns(generalStateDto.isForTicket(), GeneralState::isForTicket)
                .returns(generalStateDto.isForCi(), GeneralState::isForCi)
                .returns(generalStateDto.isForCloseState(), GeneralState::isForCloseState);
    }

    @ParameterizedTest
    @MethodSource("entitiesProvider")
    void toDto(GeneralState generalState) {
        assertThat(GENERAL_STATE_MAPPER.toDto(generalState))
                .returns(generalState.getId(), GeneralStateDto::getId)
                .returns(generalState.isForTicket(), GeneralStateDto::isForTicket)
                .returns(generalState.isForCi(), GeneralStateDto::isForCi)
                .returns(generalState.isForCloseState(), GeneralStateDto::isForCloseState);
    }

    private static Stream<Arguments> entitiesProvider() {
        return Stream.of(
                Arguments.of(GeneralState.builder().id(Long.MIN_VALUE).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(true).isForCi(true).isForCloseState(true).coefficientPrice(NumberUtils.FLOAT_MINUS_ONE).build()),
                Arguments.of(GeneralState.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(true).isForCi(true).isForCloseState(false).coefficientPrice(NumberUtils.FLOAT_ZERO).build()),
                Arguments.of(GeneralState.builder().id(NumberUtils.LONG_ZERO).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(true).isForCi(false).isForCloseState(false).coefficientPrice(NumberUtils.FLOAT_ONE).build()),
                Arguments.of(GeneralState.builder().id(NumberUtils.LONG_ONE).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(false).isForCi(false).isForCloseState(false).coefficientPrice(NumberUtils.FLOAT_MINUS_ONE).build()),
                Arguments.of(GeneralState.builder().id(Long.MAX_VALUE).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(false).isForCi(true).isForCloseState(true).coefficientPrice(NumberUtils.FLOAT_ZERO).build()),
                Arguments.of(GeneralState.builder().id(Long.MIN_VALUE).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(false).isForCi(false).isForCloseState(true).coefficientPrice(NumberUtils.FLOAT_ONE).build()),
                Arguments.of(GeneralState.builder().id(Long.MIN_VALUE).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(false).isForCi(false).isForCloseState(false).coefficientPrice(NumberUtils.FLOAT_ONE).build())
        );
    }

    private static Stream<Arguments> dtosProvider() {
        return Stream.of(
                Arguments.of(GeneralStateDto.builder().id(Long.MIN_VALUE).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(true).isForCi(true).isForCloseState(true).coefficientPrice(NumberUtils.FLOAT_MINUS_ONE).build()),
                Arguments.of(GeneralStateDto.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(true).isForCi(true).isForCloseState(false).coefficientPrice(NumberUtils.FLOAT_ZERO).build()),
                Arguments.of(GeneralStateDto.builder().id(NumberUtils.LONG_ZERO).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(true).isForCi(false).isForCloseState(false).coefficientPrice(NumberUtils.FLOAT_ONE).build()),
                Arguments.of(GeneralStateDto.builder().id(NumberUtils.LONG_ONE).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(false).isForCi(false).isForCloseState(false).coefficientPrice(NumberUtils.FLOAT_MINUS_ONE).build()),
                Arguments.of(GeneralStateDto.builder().id(Long.MAX_VALUE).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(false).isForCi(true).isForCloseState(true).coefficientPrice(NumberUtils.FLOAT_ZERO).build()),
                Arguments.of(GeneralStateDto.builder().id(Long.MIN_VALUE).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(false).isForCi(false).isForCloseState(true).coefficientPrice(NumberUtils.FLOAT_ONE).build()),
                Arguments.of(GeneralStateDto.builder().id(Long.MIN_VALUE).name(Common.GENERAL_STATE_TEST_NAME).isForTicket(false).isForCi(false).isForCloseState(false).coefficientPrice(NumberUtils.FLOAT_ONE).build())
        );
    }
}