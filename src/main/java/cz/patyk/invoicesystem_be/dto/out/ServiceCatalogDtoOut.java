package cz.patyk.invoicesystem_be.dto.out;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class ServiceCatalogDtoOut extends RepresentationModel<ServiceCatalogDtoOut> {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long vat;
    private VatDtoOut vatDtoOut;
    private int estimateTimeDelivery;
    private int estimateTimeReaction;
    private boolean isDisable;
}


