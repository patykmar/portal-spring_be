package cz.patyk.invoicesystem_be.common;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.dto.out.AddressDtoOut;
import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.entities.Country;
import cz.patyk.invoicesystem_be.entities.Vat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestConstants {
    public static final String COMPANY_TEST_NAME = "Testing company name";
    public static final String COMPANY_TEST_DESCRIPTION = "Testing company description";
    public static final String COMPANY_TEST_COMPANY_ID = "123456789";
    public static final String COMPANY_TEST_VAT_NUMBER = "CZ123456789";
    public static final Long COMPANY_TEST_CREATED = 915148800L;
    public static final Long COMPANY_TEST_MODIFY = Instant.now().getEpochSecond();
    public static final String COMPANY_TEST_ACCOUNT_NUMBER = "12345-123456789/0123";
    public static final String COMPANY_TEST_IBAN = "CZ1234567890123456789012";

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
            .id(Long.MAX_VALUE).street(ADDRESS_TEST_STREET).city(ADDRESS_TEST_CITY).zipCode(ADDRESS_TEST_ZIP_CODE).countryDto(COUNTRY_TEST_DTO).build();

    public static final String QUEUE_TEST_NAME = "test Queue name";

    public static final String VAT_TEST_NAME = "test vat name";
    public static final String GENERAL_STATE_TEST_NAME = "test general state name";

    public static final int VAT_TEST_PERCENT = 20;
    public static final int VAT_TEST_MULTIPLIER = 120;
    public static final Vat VAT_TEST_ENTITY = Vat.builder().id(LONG_ONE).name(VAT_TEST_NAME).isDefault(true).percent(VAT_TEST_PERCENT).multiplier(VAT_TEST_MULTIPLIER).build();

    public static final String TARIFF_TEST_NAME = "test tariff name";
}
