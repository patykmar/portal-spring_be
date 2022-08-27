package cz.patyk.invoicesystem_be.constant;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.dto.in.CompanyDtoIn;
import cz.patyk.invoicesystem_be.dto.in.SlaDtoIn;
import cz.patyk.invoicesystem_be.dto.in.UserDtoIn;
import cz.patyk.invoicesystem_be.dto.in.UserDtoInTwoPassword;
import cz.patyk.invoicesystem_be.dto.in.UserPasswordChangeIn;
import cz.patyk.invoicesystem_be.dto.out.AddressDtoOut;
import cz.patyk.invoicesystem_be.entities.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDtos {

    public static final CompanyDtoIn COMPANY_DTO_IN = CompanyDtoIn.builder().id(NumberUtils.LONG_ONE)
            .name(Common.COMPANY_TEST_NAME).description(Common.COMPANY_TEST_DESCRIPTION)
            .companyId(Common.COMPANY_TEST_COMPANY_ID).vatNumber(Common.COMPANY_TEST_VAT_NUMBER)
            .accountNumber(Common.COMPANY_TEST_ACCOUNT_NUMBER).iban(Common.COMPANY_TEST_IBAN).build();

    public static final CountryDto COUNTRY_DTO = CountryDto.builder()
            .id(Long.MAX_VALUE).name(Common.COUNTRY_TEST_NAME)
            .iso3166alpha3(Common.COUNTRY_TEST_ISO_3166_ALPHA_3)
            .build();

    public static final AddressDtoOut ADDRESS_TEST_DTO_OUT = AddressDtoOut.builder()
            .id(Long.MAX_VALUE).street(Common.ADDRESS_TEST_STREET)
            .city(Common.ADDRESS_TEST_CITY).zipCode(Common.ADDRESS_TEST_ZIP_CODE)
            .countryDto(COUNTRY_DTO).build();

    public static final SlaDtoIn SLA_DTO_IN = SlaDtoIn.builder()
            .id(NumberUtils.LONG_ONE)
            .tariffId(NumberUtils.LONG_ONE)
            .priorityId(NumberUtils.LONG_ONE)
            .ticketTypeId(NumberUtils.LONG_ONE)
            .reactionTime(Common.SLA_REACTION_TIME)
            .resolvedTime(Common.SLA_RESOLVED_TIME)
            .priceMultiplier(Common.SLA_PRICE_MULTIPLIER)
            .build();
    public static final SlaDtoIn SLA_DTO_IN_EDIT = SlaDtoIn.builder()
            .tariffId(2L).priorityId(2L).ticketTypeId(2L)
            .reactionTime(Common.SLA_REACTION_TIME)
            .resolvedTime(Common.SLA_RESOLVED_TIME)
            .priceMultiplier(Common.SLA_PRICE_MULTIPLIER)
            .build();

    public static final UserDtoIn USER_DTO_IN_ROLE_ADMIN = UserDtoIn.builder()
            .email(Common.USER_TEST_EMAIL_EDIT).firstName(Common.USER_TEST_FIRST_NAME_EDIT)
            .lastName(Common.USER_TEST_LAST_NAME_EDIT).roles(User.Role.ADMIN.toString())
            .employeeOfCompanyId(NumberUtils.LONG_ONE).build();

    public static final UserDtoInTwoPassword USER_DTO_IN_ROLE_USER_WITH_PASSWORD = UserDtoInTwoPassword.builder()
            .email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME)
            .employeeOfCompanyId(NumberUtils.LONG_ONE).lastName(Common.USER_TEST_LAST_NAME)
            .roles(User.Role.USER.toString()).password(Common.USER_TEST_PASSWORD_1)
            .retypePassword(Common.USER_TEST_PASSWORD_1).employeeOfCompanyId(NumberUtils.LONG_ONE)
            .build();

    public static final UserDtoInTwoPassword USER_DTO_IN_ROLE_USER_WITH_WRONG_PASSWORD = UserDtoInTwoPassword.builder()
            .email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME)
            .lastName(Common.USER_TEST_LAST_NAME).roles(User.Role.USER.toString())
            .password(Common.USER_TEST_PASSWORD_1).retypePassword(Common.USER_TEST_PASSWORD_RETYPED_WRONG)
            .employeeOfCompanyId(NumberUtils.LONG_ONE).build();

    public static final UserPasswordChangeIn USER_PASSWORD_CHANGE_IN_WRONG_OLD_PASSWORD =  UserPasswordChangeIn.builder()
            .oldPassword(Common.USER_TEST_PASSWORD_1 + "WRONG").newPassword(Common.USER_TEST_PASSWORD_1)
            .reTypedPassword(Common.USER_TEST_PASSWORD_1).build();

    public static final UserPasswordChangeIn USER_PASSWORD_CHANGE_IN_WRONG_RETYPED_PASSWORD =  UserPasswordChangeIn.builder()
            .oldPassword(Common.USER_TEST_PASSWORD_1).newPassword(Common.USER_TEST_PASSWORD_2)
            .reTypedPassword(Common.USER_TEST_PASSWORD_2+ "WRONG").build();

    public static final UserPasswordChangeIn USER_PASSWORD_CHANGE_CORRECT =  UserPasswordChangeIn.builder()
            .oldPassword(Common.USER_TEST_PASSWORD_1).newPassword(Common.USER_TEST_PASSWORD_2)
            .reTypedPassword(Common.USER_TEST_PASSWORD_2).build();
}
