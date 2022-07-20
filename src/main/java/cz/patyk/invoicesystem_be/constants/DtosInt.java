package cz.patyk.invoicesystem_be.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DtosInt {
    private static final String DESCRIPTION = "Description";
    private static final String ESTIMATE_TIME_DELIVERY = "Estimate time delivery";
    private static final String ESTIMATE_TIME_REACTION = "Estimate time reaction";
    private static final String NAME = "Name";
    private static final String PRICE = "Price";
    private static final String VAT = "Vat";

    private static final String POSITIVE_MESSAGE = " should be greater then zero";
    private static final String NOT_NULL_MESSAGE = " cannot be null or empty";

    public static final String VALIDATION_MESSAGE_DESCRIPTION_NOT_NULL = DESCRIPTION + NOT_NULL_MESSAGE;
    public static final String VALIDATION_MESSAGE_NAME_NOT_NULL = NAME + NOT_NULL_MESSAGE;
    public static final String VALIDATION_MESSAGE_PRICE_POSITIVE = PRICE + POSITIVE_MESSAGE;
    public static final String VALIDATION_MESSAGE_PRICE_NOT_BLANK = PRICE + NOT_NULL_MESSAGE;
    public static final String VALIDATION_MESSAGE_VAT_ID_POSITIVE = VAT + POSITIVE_MESSAGE;
    public static final String VALIDATION_MESSAGE_VAT_ID_NOT_NULL = VAT + NOT_NULL_MESSAGE;
    public static final String VALIDATION_MESSAGE_ESTIMATE_TIME_DELIVERY_POSITIVE = ESTIMATE_TIME_DELIVERY + POSITIVE_MESSAGE;
    public static final String VALIDATION_MESSAGE_ESTIMATE_TIME_REACTION_POSITIVE = ESTIMATE_TIME_REACTION + POSITIVE_MESSAGE;
}
