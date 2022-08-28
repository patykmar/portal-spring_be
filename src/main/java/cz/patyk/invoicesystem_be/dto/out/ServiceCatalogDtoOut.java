package cz.patyk.invoicesystem_be.dto.out;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class ServiceCatalogDtoOut extends RepresentationModel<ServiceCatalogDtoOut> {
    private final Long id;
    private final String name;
    private final String description;
    private final Long price;
    private final Long vat;
    private final VatDtoOut vatDtoOut;
    private final int estimateTimeDelivery;
    private final int estimateTimeReaction;
    private final boolean isDisable;
}


