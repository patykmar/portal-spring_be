package cz.patyk.invoicesystem_be.common;

import cz.patyk.invoicesystem_be.entities.Vat;

public class TestConstants {
    public static final Long LONG_MIN = Long.MIN_VALUE;
    public static final Long LONG_NEGATIVE = -1L;
    public static final Long LONG_ZERO = 0L;
    public static final Long LONG_POSITIVE = 1L;
    public static final Long LONG_MAX = Long.MAX_VALUE;

    public static final int INT_MIN = Integer.MIN_VALUE;
    public static final int INT_NEGATIVE = -1;
    public static final int INT_ZERO = 0;
    public static final int INT_POSITIVE = 1;
    public static final int INT_MAX = Integer.MAX_VALUE;

    public static final String TEST_VAT_NAME = "test vat name";
    public static final String TEST_TARIFF_NAME = "test tariff name";

    public static final int FK_VAT_PERCENT = 20;
    public static final int FK_VAT_MULTIPLIER = 120;
    public static final Vat FK_VAT = Vat.builder().id(LONG_POSITIVE).name(TEST_VAT_NAME).isDefault(true).percent(FK_VAT_PERCENT).multiplier(FK_VAT_MULTIPLIER).build();
}
