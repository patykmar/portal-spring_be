package cz.patyk.invoicesystem_be.common;

import cz.patyk.invoicesystem_be.entities.PaymentType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static cz.patyk.invoicesystem_be.common.TestConstants.PAYMENT_TYPE_TEST_NAME;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestEntityConstants {
    public static final PaymentType PAYMENT_TYPE_ENTITY = PaymentType.builder()
            .name(PAYMENT_TYPE_TEST_NAME).isDefault(false).build();
    public static final PaymentType PAYMENT_TYPE_ENTITY_EDIT = PaymentType.builder()
            .name(PAYMENT_TYPE_TEST_NAME + " edited").isDefault(true).build();
}
