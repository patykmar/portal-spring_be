package cz.patyk.invoicesystem_be.constant;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.dto.out.AddressDtoOut;
import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.entities.Country;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;


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
    public static final Country COUNTRY_TEST_ENTITY = new Country(Long.MAX_VALUE, COUNTRY_TEST_NAME, COUNTRY_TEST_ISO_3166_ALPHA_3, List.of());
    public static final CountryDto COUNTRY_TEST_DTO = CountryDto.builder()
            .id(Long.MAX_VALUE).name(COUNTRY_TEST_NAME).iso3166alpha3(COUNTRY_TEST_ISO_3166_ALPHA_3).build();

    public static final String ADDRESS_TEST_STREET = "Fake street 123";
    public static final String ADDRESS_TEST_CITY = "Springfield";
    public static final String ADDRESS_TEST_ZIP_CODE = "12345";
    public static final Address ADDRESS_TEST_ENTITY = new Address(Long.MAX_VALUE, ADDRESS_TEST_STREET, ADDRESS_TEST_CITY, ADDRESS_TEST_ZIP_CODE, COUNTRY_TEST_ENTITY);
    public static final AddressDtoOut ADDRESS_TEST_DTO_OUT = AddressDtoOut.builder()
            .id(Long.MAX_VALUE).street(ADDRESS_TEST_STREET).city(ADDRESS_TEST_CITY)
            .zipCode(ADDRESS_TEST_ZIP_CODE).countryDto(COUNTRY_TEST_DTO).build();

    public static final String INFLUENCING_TICKET_TEST_NAME = "test Influencing ticket name";

    public static final String PAYMENT_TYPE_TEST_NAME = "test Payment Type name";

    public static final String QUEUE_TEST_NAME = "test Queue name";

    public static final String USER_TEST_EMAIL = "test@example.com";
    public static final String USER_TEST_EMAIL_EDIT = "test-edited@example.com";
    public static final String USER_TEST_FIRST_NAME = "Homer";
    public static final String USER_TEST_FIRST_NAME_EDIT = "Homer J.";
    public static final String USER_TEST_LAST_NAME = "Simpson";
    public static final String USER_TEST_LAST_NAME_EDIT = "Simpson edited";
    public static final String USER_TEST_PASSWORD = "password1";
    public static final String USER_TEST_PASSWORD_ENCODE = "$2a$10$ysCS4ksIsTqUJPsEaND6s.NoELDCtv9fp/P2pVGF21h03wa7JnPKi";
    public static final String USER_TEST_PASSWORD_RETYPED_WRONG = "wrong password";
    public static final Long USER_TEST_LAST_LOGIN = Instant.now().getEpochSecond();
    public static final Long USER_TEST_CREATED = 315532800L;
    public static final Long USER_TEST_PASSWORD_CHANGED = 325532800L;

    public static final String VAT_TEST_NAME = "test vat name";
    public static final int VAT_TEST_PERCENT = 20;
    public static final int VAT_TEST_MULTIPLIER = 120;


    public static final String TARIFF_TEST_NAME = "test tariff name";

    public static final String TICKET_TYPE_TEST_NAME = "test Ticket type name";
    public static final String TICKET_TYPE_TEST_ABBREVIATION = "test Ticket type abbreviation";
}
