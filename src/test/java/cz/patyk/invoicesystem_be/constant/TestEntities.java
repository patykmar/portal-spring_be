package cz.patyk.invoicesystem_be.constant;

import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.entities.Company;
import cz.patyk.invoicesystem_be.entities.Country;
import cz.patyk.invoicesystem_be.entities.PaymentType;
import cz.patyk.invoicesystem_be.entities.ServiceCatalog;
import cz.patyk.invoicesystem_be.entities.User;
import cz.patyk.invoicesystem_be.entities.Vat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestEntities {

    public static final Country COUNTRY_TEST = Country.builder().id(Long.MAX_VALUE).name(Common.COUNTRY_TEST_NAME)
            .iso3166alpha3(Common.COUNTRY_TEST_ISO_3166_ALPHA_3).addressList(List.of()).build();

    public static final Address ADDRESS_TEST = Address.builder().id(Long.MAX_VALUE).street(Common.ADDRESS_TEST_STREET)
            .city(Common.ADDRESS_TEST_CITY).zipCode(Common.ADDRESS_TEST_ZIP_CODE).country(COUNTRY_TEST).build();

    public static final Company COMPANY_TEST = Company.builder().id(NumberUtils.LONG_ONE)
            .name(Common.COMPANY_TEST_NAME).description(Common.COMPANY_TEST_DESCRIPTION)
            .companyId(Common.COMPANY_TEST_COMPANY_ID).vatNumber(Common.COMPANY_TEST_VAT_NUMBER)
            .created(Common.COMPANY_TEST_CREATED).modify(Common.COMPANY_TEST_MODIFY)
            .accountNumber(Common.COMPANY_TEST_ACCOUNT_NUMBER).iban(Common.COMPANY_TEST_IBAN).build();

    public static final PaymentType PAYMENT_TYPE = PaymentType.builder()
            .name(Common.PAYMENT_TYPE_TEST_NAME).isDefault(false).build();
    public static final PaymentType PAYMENT_TYPE_EDIT = PaymentType.builder()
            .name(Common.PAYMENT_TYPE_TEST_NAME + " edited").isDefault(true).build();

    public static final User USER = User.builder()
            .id(NumberUtils.LONG_ONE).firstName(Common.USER_TEST_FIRST_NAME)
            .lastName(Common.USER_TEST_LAST_NAME).email(Common.USER_TEST_EMAIL)
            .employeeOfCompanyId(TestEntities.COMPANY_TEST)
            .roles(User.Role.USER).password(Common.USER_TEST_PASSWORD_ENCODE)
            .lastLogin(Common.USER_TEST_LAST_LOGIN).createdDate(Common.USER_TEST_CREATED)
            .passwordChanged(Common.USER_TEST_PASSWORD_CHANGED).build();

    public static final Vat VAT_ENTITY = Vat.builder().id(NumberUtils.LONG_ONE)
            .name(Common.VAT_TEST_NAME).isDefault(true).percent(Common.VAT_TEST_PERCENT)
            .multiplier(Common.VAT_TEST_MULTIPLIER).build();
    public static final Vat VAT_ENTITY_2 = Vat.builder().id(2L)
            .name(Common.VAT_TEST_NAME + " 2").isDefault(false).percent(Common.VAT_TEST_PERCENT + 2)
            .multiplier(Common.VAT_TEST_MULTIPLIER + 2).build();

    public static final ServiceCatalog SERVICE_CATALOG_1 = ServiceCatalog.builder().id(NumberUtils.LONG_ONE)
            .name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION)
            .price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY)
            .estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).vat(VAT_ENTITY).build();

    public static final ServiceCatalog SERVICE_CATALOG_2 = ServiceCatalog.builder().id(2L)
            .name(Common.SERVICE_CATALOG_NAME + " 2").description(Common.SERVICE_CATALOG_DESCRIPTION + " 2")
            .price(Common.SERVICE_CATALOG_PRICE + 2L).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY +2)
            .estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION + 2).vat(VAT_ENTITY_2).build();

    public static final ServiceCatalog SERVICE_CATALOG_3 = ServiceCatalog.builder().id(3L)
            .name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION)
            .price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY)
            .estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).vat(VAT_ENTITY).build();

    public static final List<ServiceCatalog> SERVICE_CATALOG_LIST = List.of(SERVICE_CATALOG_1, SERVICE_CATALOG_2, SERVICE_CATALOG_3);
}
