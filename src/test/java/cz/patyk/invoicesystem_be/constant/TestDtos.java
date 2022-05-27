package cz.patyk.invoicesystem_be.constant;

import cz.patyk.invoicesystem_be.dto.in.CompanyDtoIn;
import cz.patyk.invoicesystem_be.dto.in.UserDtoIn;
import cz.patyk.invoicesystem_be.dto.in.UserDtoInTwoPassword;
import cz.patyk.invoicesystem_be.entities.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDtos {

    public static final CompanyDtoIn COMPANY_TEST_DTO_IN = CompanyDtoIn.builder().id(NumberUtils.LONG_ONE)
            .name(Common.COMPANY_TEST_NAME).description(Common.COMPANY_TEST_DESCRIPTION)
            .companyId(Common.COMPANY_TEST_COMPANY_ID).vatNumber(Common.COMPANY_TEST_VAT_NUMBER)
            .accountNumber(Common.COMPANY_TEST_ACCOUNT_NUMBER).iban(Common.COMPANY_TEST_IBAN).build();

    public static final UserDtoIn USER_DTO_IN_ROLE_ADMIN = UserDtoIn.builder()
            .email(Common.USER_TEST_EMAIL_EDIT).firstName(Common.USER_TEST_FIRST_NAME_EDIT)
            .lastName(Common.USER_TEST_LAST_NAME_EDIT).roles(User.Role.ADMIN.toString())
            .employeeOfCompanyId(NumberUtils.LONG_ONE).build();

    public static final UserDtoInTwoPassword USER_DTO_IN_ROLE_USER_WITH_PASSWORD = UserDtoInTwoPassword.builder()
            .email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME)
            .employeeOfCompanyId(NumberUtils.LONG_ONE).lastName(Common.USER_TEST_LAST_NAME)
            .roles(User.Role.USER.toString()).password(Common.USER_TEST_PASSWORD)
            .retypePassword(Common.USER_TEST_PASSWORD).employeeOfCompanyId(NumberUtils.LONG_ONE)
            .build();

    public static final UserDtoInTwoPassword USER_DTO_IN_ROLE_USER_WITH_WRONG_PASSWORD = UserDtoInTwoPassword.builder()
            .email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME)
            .lastName(Common.USER_TEST_LAST_NAME).roles(User.Role.USER.toString())
            .password(Common.USER_TEST_PASSWORD).retypePassword(Common.USER_TEST_PASSWORD_RETYPED_WRONG)
            .employeeOfCompanyId(NumberUtils.LONG_ONE).build();
}
