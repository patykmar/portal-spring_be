package cz.patyk.invoicesystem_be.constant;

import cz.patyk.invoicesystem_be.entities.Company;
import cz.patyk.invoicesystem_be.entities.PaymentType;
import cz.patyk.invoicesystem_be.entities.User;
import cz.patyk.invoicesystem_be.entities.Vat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestEntities {
    public static final Company COMPANY_TEST_ENTITY = Company.builder().id(NumberUtils.LONG_ONE)
            .name(Common.COMPANY_TEST_NAME).description(Common.COMPANY_TEST_DESCRIPTION)
            .companyId(Common.COMPANY_TEST_COMPANY_ID).vatNumber(Common.COMPANY_TEST_VAT_NUMBER)
            .created(Common.COMPANY_TEST_CREATED).modify(Common.COMPANY_TEST_MODIFY)
            .accountNumber(Common.COMPANY_TEST_ACCOUNT_NUMBER).iban(Common.COMPANY_TEST_IBAN).build();

    public static final PaymentType PAYMENT_TYPE_ENTITY = PaymentType.builder()
            .name(Common.PAYMENT_TYPE_TEST_NAME).isDefault(false).build();
    public static final PaymentType PAYMENT_TYPE_ENTITY_EDIT = PaymentType.builder()
            .name(Common.PAYMENT_TYPE_TEST_NAME + " edited").isDefault(true).build();

    public static final User USER_ENTITY = User.builder()
            .id(NumberUtils.LONG_ONE).firstName(Common.USER_TEST_FIRST_NAME)
            .lastName(Common.USER_TEST_LAST_NAME).email(Common.USER_TEST_EMAIL)
            .employeeOfCompanyId(TestEntities.COMPANY_TEST_ENTITY)
            .roles(User.Role.USER).password(Common.USER_TEST_PASSWORD_ENCODE)
            .lastLogin(Common.USER_TEST_LAST_LOGIN).createdDate(Common.USER_TEST_CREATED)
            .passwordChanged(Common.USER_TEST_PASSWORD_CHANGED).build();

    public static final Vat VAT_TEST_ENTITY = Vat.builder().id(NumberUtils.LONG_ONE)
            .name(Common.VAT_TEST_NAME).isDefault(true).percent(Common.VAT_TEST_PERCENT)
            .multiplier(Common.VAT_TEST_MULTIPLIER).build();
}
