package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.PaymentTypeDto;
import cz.patyk.invoicesystem_be.entities.PaymentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static cz.patyk.invoicesystem_be.common.TestConstants.PAYMENT_TYPE_TEST_NAME;
import static org.apache.commons.lang3.math.NumberUtils.LONG_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentTypeMapperTest {
    private static final PaymentTypeMapper PAYMENT_TYPE_MAPPER = Mappers.getMapper(PaymentTypeMapper.class);

    @ParameterizedTest
    @MethodSource("entityProvider")
    void toDto(PaymentType paymentType) {
        assertThat(PAYMENT_TYPE_MAPPER.toDto(paymentType))
                .returns(paymentType.getId(), PaymentTypeDto::getId)
                .returns(paymentType.getName(), PaymentTypeDto::getName)
                .returns(paymentType.isDefault(), PaymentTypeDto::isDefault);
    }

    @ParameterizedTest
    @MethodSource("dtoProvider")
    void toEntity(PaymentTypeDto paymentTypeDto) {
        assertThat(PAYMENT_TYPE_MAPPER.toEntity(paymentTypeDto))
                .returns(paymentTypeDto.getId(), PaymentType::getId)
                .returns(paymentTypeDto.getName(), PaymentType::getName)
                .returns(paymentTypeDto.isDefault(), PaymentType::isDefault);
    }

    private static Stream<Arguments> entityProvider(){
        return Stream.of(
                Arguments.of(PaymentType.builder().id(Long.MIN_VALUE).name(PAYMENT_TYPE_TEST_NAME).isDefault(true).build()),
                Arguments.of(PaymentType.builder().id(LONG_MINUS_ONE).name(PAYMENT_TYPE_TEST_NAME).isDefault(false).build()),
                Arguments.of(PaymentType.builder().id(LONG_ZERO).name(PAYMENT_TYPE_TEST_NAME).isDefault(true).build()),
                Arguments.of(PaymentType.builder().id(LONG_ONE).name(PAYMENT_TYPE_TEST_NAME).isDefault(false).build()),
                Arguments.of(PaymentType.builder().id(Long.MAX_VALUE).name(PAYMENT_TYPE_TEST_NAME).isDefault(true).build())
        );
    }

    private static Stream<Arguments> dtoProvider() {
        return Stream.of(
                Arguments.of(PaymentTypeDto.builder().id(Long.MIN_VALUE).name(PAYMENT_TYPE_TEST_NAME).isDefault(true).build()),
                Arguments.of(PaymentTypeDto.builder().id(LONG_MINUS_ONE).name(PAYMENT_TYPE_TEST_NAME).isDefault(false).build()),
                Arguments.of(PaymentTypeDto.builder().id(LONG_ZERO).name(PAYMENT_TYPE_TEST_NAME).isDefault(true).build()),
                Arguments.of(PaymentTypeDto.builder().id(LONG_ONE).name(PAYMENT_TYPE_TEST_NAME).isDefault(false).build()),
                Arguments.of(PaymentTypeDto.builder().id(Long.MAX_VALUE).name(PAYMENT_TYPE_TEST_NAME).isDefault(true).build())
        );
    }
}