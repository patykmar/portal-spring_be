package cz.patyk.invoicesystem_be.common;

import cz.patyk.invoicesystem_be.dto.in.UserDtoIn;
import cz.patyk.invoicesystem_be.dto.in.UserDtoInTwoPassword;
import cz.patyk.invoicesystem_be.entities.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_EMAIL;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_FIRST_NAME;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_LAST_NAME;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_PASSWORD;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDtoConstants {
    public static final UserDtoIn USER_DTO_IN_ROLE_USER = UserDtoIn.builder()
            .email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME)
            .lastName(USER_TEST_LAST_NAME).roles(User.Role.USER.toString())
            .employeeOfCompanyId(LONG_ONE).build();

    public static final UserDtoInTwoPassword USER_DTO_IN_ROLE_USER_WITH_PASSWORD = UserDtoInTwoPassword.builder()
            .email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME)
            .employeeOfCompanyId(LONG_ONE).lastName(USER_TEST_LAST_NAME)
            .roles(User.Role.USER.toString()).password(USER_TEST_PASSWORD)
            .retypePassword(USER_TEST_PASSWORD).employeeOfCompanyId(LONG_ONE)
            .build();

    public static final UserDtoInTwoPassword USER_DTO_IN_ROLE_USER_WITH_WRONG_PASSWORD = UserDtoInTwoPassword.builder()
            .email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME)
            .lastName(USER_TEST_LAST_NAME).roles(User.Role.USER.toString())
            .password(USER_TEST_PASSWORD).retypePassword(USER_TEST_PASSWORD + "wrong")
            .employeeOfCompanyId(LONG_ONE).build();
}
