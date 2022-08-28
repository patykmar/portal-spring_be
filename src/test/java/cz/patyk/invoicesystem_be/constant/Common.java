package cz.patyk.invoicesystem_be.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Common {
    public static final String COMPANY_TEST_NAME = "Testing company name";
    public static final String COMPANY_TEST_DESCRIPTION = "Testing company description";
    public static final String COMPANY_TEST_COMPANY_ID = "123456789";
    public static final String COMPANY_TEST_VAT_NUMBER = "CZ123456789";
    public static final Long COMPANY_TEST_CREATED = 915148800L;
    public static final Long COMPANY_TEST_MODIFY = Instant.now().getEpochSecond();
    public static final String COMPANY_TEST_ACCOUNT_NUMBER = "12345-123456789/0123";
    public static final String COMPANY_TEST_IBAN = "CZ1234567890123456789012";

    public static final String GENERAL_STATE_TEST_NAME = "test general state name";

    public static final String COUNTRY_TEST_NAME = "Val Verde";
    public static final String COUNTRY_TEST_ISO_3166_ALPHA_3 = "VV";

    public static final String ADDRESS_TEST_STREET = "Fake street 123";
    public static final String ADDRESS_TEST_CITY = "Springfield";
    public static final String ADDRESS_TEST_ZIP_CODE = "12345";

    public static final String INFLUENCING_TICKET_PRIORITY_TEST_NAME = "Influencing ticket priority name";
    public static final String INFLUENCING_TICKET_IMPACT_TEST_NAME = "Influencing ticket impact name";

    public static final String PAYMENT_TYPE_TEST_NAME = "test Payment Type name";

    public static final String QUEUE_TEST_NAME = "test Queue name";

    public static final int SLA_REACTION_TIME = 4321;
    public static final int SLA_RESOLVED_TIME = 12345;
    public static final int SLA_PRICE_MULTIPLIER = 150;

    public static final String USER_TEST_EMAIL = "test@example.com";
    public static final String USER_TEST_EMAIL_EDIT = "test-edited@example.com";
    public static final String USER_TEST_FIRST_NAME = "Homer";
    public static final String USER_TEST_FIRST_NAME_EDIT = "Homer J.";
    public static final String USER_TEST_LAST_NAME = "Simpson";
    public static final String USER_TEST_LAST_NAME_EDIT = "Simpson edited";
    public static final String USER_TEST_PASSWORD_1 = "password1";
    public static final String USER_TEST_PASSWORD_2 = "password2";
    public static final String USER_TEST_PASSWORD_ENCODE = "$2a$10$ysCS4ksIsTqUJPsEaND6s.NoELDCtv9fp/P2pVGF21h03wa7JnPKi";
    public static final String USER_TEST_PASSWORD_RETYPED_WRONG = "wrong password";
    public static final Long USER_TEST_LAST_LOGIN = Instant.now().getEpochSecond();
    public static final Long USER_TEST_CREATED = 315532800L;
    public static final Long USER_TEST_PASSWORD_CHANGED = 325532800L;

    public static final String VAT_TEST_NAME = "test vat name";
    public static final int VAT_TEST_PERCENT_20 = 20;
    public static final int VAT_TEST_PERCENT_21 = 21;
    public static final int VAT_TEST_MULTIPLIER_120 = 120;
    public static final int VAT_TEST_MULTIPLIER_121 = 121;


    public static final String TARIFF_TEST_NAME = "test tariff name";

    public static final String TICKET_TYPE_TEST_NAME = "test Ticket type name";
    public static final String TICKET_TYPE_TEST_ABBREVIATION = "test Ticket type abbreviation";
}
