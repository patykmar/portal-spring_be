package cz.patyk.invoicesystem_be.common;

import cz.patyk.invoicesystem_be.entities.PaymentType;
import cz.patyk.invoicesystem_be.entities.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static cz.patyk.invoicesystem_be.common.TestConstants.COMPANY_TEST_ENTITY;
import static cz.patyk.invoicesystem_be.common.TestConstants.PAYMENT_TYPE_TEST_NAME;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_CREATED;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_EMAIL;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_FIRST_NAME;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_LAST_LOGIN;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_LAST_NAME;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_PASSWORD_CHANGED;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_PASSWORD_ENCODE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestEntityConstants {
    public static final PaymentType PAYMENT_TYPE_ENTITY = PaymentType.builder()
            .name(PAYMENT_TYPE_TEST_NAME).isDefault(false).build();
    public static final PaymentType PAYMENT_TYPE_ENTITY_EDIT = PaymentType.builder()
            .name(PAYMENT_TYPE_TEST_NAME + " edited").isDefault(true).build();

    public static final User USER_ENTITY = User.builder()
            .id(LONG_ONE).firstName(USER_TEST_FIRST_NAME)
            .lastName(USER_TEST_LAST_NAME).email(USER_TEST_EMAIL)
            .employeeOfCompanyId(COMPANY_TEST_ENTITY)
            .roles(User.Role.USER).password(USER_TEST_PASSWORD_ENCODE)
            .lastLogin(USER_TEST_LAST_LOGIN).createdDate(USER_TEST_CREATED)
            .passwordChanged(USER_TEST_PASSWORD_CHANGED).build();
}
