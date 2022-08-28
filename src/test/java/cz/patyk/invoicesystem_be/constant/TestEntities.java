package cz.patyk.invoicesystem_be.constant;

import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.entities.Company;
import cz.patyk.invoicesystem_be.entities.Country;
import cz.patyk.invoicesystem_be.entities.InfluencingTicket;
import cz.patyk.invoicesystem_be.entities.PaymentType;
import cz.patyk.invoicesystem_be.entities.Sla;
import cz.patyk.invoicesystem_be.entities.Tariff;
import cz.patyk.invoicesystem_be.entities.TicketType;
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

    public static final InfluencingTicket PRIORITY_01 = makeInfluencingTicket(NumberUtils.LONG_ONE, true, false, Long.MIN_VALUE);
    public static final InfluencingTicket PRIORITY_02 = makeInfluencingTicket(2L, true, false, Long.MAX_VALUE);
    public static final InfluencingTicket IMPACT_01 = makeInfluencingTicket(3L, false, true, Long.MIN_VALUE);
    public static final InfluencingTicket IMPACT_02 = makeInfluencingTicket(4L, false, true, Long.MAX_VALUE);
    public static final List<InfluencingTicket> INFLUENCING_TICKET_LIST = List.of(PRIORITY_01, PRIORITY_02, IMPACT_01, IMPACT_02);

    private static InfluencingTicket makeInfluencingTicket(Long id, boolean priority, boolean impact, Long coefficient) {
        return InfluencingTicket.builder()
                .id(id).name(Common.INFLUENCING_TICKET_PRIORITY_TEST_NAME + " " + id)
                .isForPriority(priority)
                .isForImpact(impact)
                .coefficientPrice(coefficient)
                .coefficientTime(coefficient)
                .build();
    }

    public static final Vat VAT_01 = Vat.builder().id(NumberUtils.LONG_ONE)
            .name(Common.VAT_TEST_NAME).isDefault(true).percent(Common.VAT_TEST_PERCENT_20)
            .multiplier(Common.VAT_TEST_MULTIPLIER_120).build();
    public static final Vat VAT_02 = Vat.builder().id(2L)
            .name(Common.VAT_TEST_NAME + " 2")
            .isDefault(false).percent(Common.VAT_TEST_PERCENT_21)
            .multiplier(Common.VAT_TEST_MULTIPLIER_121).build();
    public static final Tariff TARIFF_01 = Tariff.builder().id(NumberUtils.LONG_ONE)
            .name(Common.TARIFF_TEST_NAME).vat(TestEntities.VAT_01)
            .price(Long.MAX_VALUE).build();
    public static final Tariff TARIFF_02 = Tariff.builder()
            .id(2L).name(Common.TARIFF_TEST_NAME)
            .vat(TestEntities.VAT_02)
            .price(Long.MAX_VALUE).build();

    public static final TicketType TICKET_TYPE_01 = makeTicketType(NumberUtils.LONG_ONE, true, Long.MIN_VALUE);
    public static final TicketType TICKET_TYPE_02 = makeTicketType(2L, false, Long.MAX_VALUE);

    private static TicketType makeTicketType(Long id, boolean isDisable, Long coefficient) {
        return TicketType.builder().id(id)
                .name(Common.TICKET_TYPE_TEST_NAME + " " + id)
                .abbreviation(Common.TICKET_TYPE_TEST_ABBREVIATION + " " + id)
                .isDisable(isDisable).coefficientPrice(coefficient)
                .coefficientTime(coefficient).build();
    }

    public static final Sla SLA_01 = makeSla(NumberUtils.LONG_ONE, TestEntities.TARIFF_01, TestEntities.TICKET_TYPE_01, TestEntities.PRIORITY_01);
    public static final Sla SLA_01_EDIT = makeSla(NumberUtils.LONG_ONE, TestEntities.TARIFF_02, TestEntities.TICKET_TYPE_02, TestEntities.PRIORITY_02);
    public static final Sla SLA_02 = makeSla(2L, TestEntities.TARIFF_02, TestEntities.TICKET_TYPE_02, TestEntities.PRIORITY_02);

    private static Sla makeSla(Long id, Tariff tariff, TicketType ticketType, InfluencingTicket influencingTicket) {
        return Sla.builder().id(id).tariff(tariff).ticketType(ticketType)
                .priority(influencingTicket).reactionTime(Common.SLA_REACTION_TIME)
                .resolvedTime(Common.SLA_RESOLVED_TIME)
                .priceMultiplier(Common.SLA_PRICE_MULTIPLIER).build();
    }

    public static final List<Sla> SLA_LIST = List.of(SLA_01, SLA_02);


    public static final User USER = User.builder()
            .id(NumberUtils.LONG_ONE).firstName(Common.USER_TEST_FIRST_NAME)
            .lastName(Common.USER_TEST_LAST_NAME).email(Common.USER_TEST_EMAIL)
            .employeeOfCompanyId(TestEntities.COMPANY_TEST)
            .roles(User.Role.USER).password(Common.USER_TEST_PASSWORD_ENCODE)
            .lastLogin(Common.USER_TEST_LAST_LOGIN).createdDate(Common.USER_TEST_CREATED)
            .passwordChanged(Common.USER_TEST_PASSWORD_CHANGED).build();

    public static final Vat VAT_ENTITY_01 = Vat.builder().id(NumberUtils.LONG_ONE)
            .name(Common.VAT_TEST_NAME).isDefault(true).percent(Common.VAT_TEST_PERCENT_20)
            .multiplier(Common.VAT_TEST_MULTIPLIER_120).build();
    public static final Vat VAT_ENTITY_02 = Vat.builder().id(2L)
            .name(Common.VAT_TEST_NAME + " 2").isDefault(false).percent(Common.VAT_TEST_PERCENT_21)
            .multiplier(Common.VAT_TEST_MULTIPLIER_121).build();

    public static final ServiceCatalog SERVICE_CATALOG_01 = ServiceCatalog.builder().id(NumberUtils.LONG_ONE)
            .name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION)
            .price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY)
            .estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).vat(VAT_ENTITY_01).build();
    public static final ServiceCatalog SERVICE_CATALOG_02 = ServiceCatalog.builder().id(2L)
            .name(Common.SERVICE_CATALOG_NAME + " 2").description(Common.SERVICE_CATALOG_DESCRIPTION + " 2")
            .price(Common.SERVICE_CATALOG_PRICE + 2L).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY + 2)
            .estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION + 2).vat(VAT_ENTITY_02).build();
    public static final ServiceCatalog SERVICE_CATALOG_03 = ServiceCatalog.builder().id(3L)
            .name(Common.SERVICE_CATALOG_NAME).description(Common.SERVICE_CATALOG_DESCRIPTION)
            .price(Common.SERVICE_CATALOG_PRICE).estimateTimeDelivery(Common.SERVICE_CATALOG_ESTIMATION_TIME_DELIVERY)
            .estimateTimeReaction(Common.SERVICE_CATALOG_ESTIMATION_TIME_REACTION).vat(VAT_ENTITY_01).build();
    public static final List<ServiceCatalog> SERVICE_CATALOG_LIST = List.of(SERVICE_CATALOG_01, SERVICE_CATALOG_02, SERVICE_CATALOG_03);
}
