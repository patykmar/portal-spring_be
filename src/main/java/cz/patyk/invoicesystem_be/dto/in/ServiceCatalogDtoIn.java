package cz.patyk.invoicesystem_be.dto.in;

import cz.patyk.invoicesystem_be.constants.DtosInt;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
public class ServiceCatalogDtoIn {
    private Long id;

    @NotBlank(message = DtosInt.VALIDATION_MESSAGE_NAME_NOT_NULL)
    private String name;

    @NotBlank(message = DtosInt.VALIDATION_MESSAGE_DESCRIPTION_NOT_NULL)
    private String description;

    @NotNull(message = DtosInt.VALIDATION_MESSAGE_PRICE_NOT_BLANK)
    @Positive(message = DtosInt.VALIDATION_MESSAGE_PRICE_POSITIVE)
    private Long price;

    @NotNull(message = DtosInt.VALIDATION_MESSAGE_VAT_ID_NOT_NULL)
    @Positive(message = DtosInt.VALIDATION_MESSAGE_VAT_ID_POSITIVE)
    private Long vat;

    @Positive(message = DtosInt.VALIDATION_MESSAGE_ESTIMATE_TIME_DELIVERY_POSITIVE)
    private int estimateTimeDelivery;

    @Positive(message = DtosInt.VALIDATION_MESSAGE_ESTIMATE_TIME_REACTION_POSITIVE)
    private int estimateTimeReaction;

    @NotNull
    private boolean isDisable;
}
