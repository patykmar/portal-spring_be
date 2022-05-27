package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.dto.out.PaymentTypeDtoOut;
import cz.patyk.invoicesystem_be.entities.PaymentType;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentTypeMapperTest {
    private static final PaymentTypeMapper PAYMENT_TYPE_MAPPER = Mappers.getMapper(PaymentTypeMapper.class);

    @ParameterizedTest
    @MethodSource("entityProvider")
    void toDto(PaymentType paymentType) {
        assertThat(PAYMENT_TYPE_MAPPER.toDto(paymentType))
                .returns(paymentType.getId(), PaymentTypeDtoOut::getId)
                .returns(paymentType.getName(), PaymentTypeDtoOut::getName)
                .returns(paymentType.isDefault(), PaymentTypeDtoOut::isDefault);
    }

    @ParameterizedTest
    @MethodSource("dtoProvider")
    void toEntity(PaymentTypeDtoOut paymentTypeDtoOut) {
        assertThat(PAYMENT_TYPE_MAPPER.toEntity(paymentTypeDtoOut))
                .returns(paymentTypeDtoOut.getId(), PaymentType::getId)
                .returns(paymentTypeDtoOut.getName(), PaymentType::getName)
                .returns(paymentTypeDtoOut.isDefault(), PaymentType::isDefault);
    }

    private static Stream<Arguments> entityProvider(){
        return Stream.of(
                Arguments.of(PaymentType.builder().id(Long.MIN_VALUE).name(Common.PAYMENT_TYPE_TEST_NAME).isDefault(true).build()),
                Arguments.of(PaymentType.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.PAYMENT_TYPE_TEST_NAME).isDefault(false).build()),
                Arguments.of(PaymentType.builder().id(NumberUtils.LONG_ZERO).name(Common.PAYMENT_TYPE_TEST_NAME).isDefault(true).build()),
                Arguments.of(PaymentType.builder().id(NumberUtils.LONG_ONE).name(Common.PAYMENT_TYPE_TEST_NAME).isDefault(false).build()),
                Arguments.of(PaymentType.builder().id(Long.MAX_VALUE).name(Common.PAYMENT_TYPE_TEST_NAME).isDefault(true).build())
        );
    }

    private static Stream<Arguments> dtoProvider() {
        return Stream.of(
                Arguments.of(PaymentTypeDtoOut.builder().id(Long.MIN_VALUE).name(Common.PAYMENT_TYPE_TEST_NAME).isDefault(true).build()),
                Arguments.of(PaymentTypeDtoOut.builder().id(NumberUtils.LONG_MINUS_ONE).name(Common.PAYMENT_TYPE_TEST_NAME).isDefault(false).build()),
                Arguments.of(PaymentTypeDtoOut.builder().id(NumberUtils.LONG_ZERO).name(Common.PAYMENT_TYPE_TEST_NAME).isDefault(true).build()),
                Arguments.of(PaymentTypeDtoOut.builder().id(NumberUtils.LONG_ONE).name(Common.PAYMENT_TYPE_TEST_NAME).isDefault(false).build()),
                Arguments.of(PaymentTypeDtoOut.builder().id(Long.MAX_VALUE).name(Common.PAYMENT_TYPE_TEST_NAME).isDefault(true).build())
        );
    }
}