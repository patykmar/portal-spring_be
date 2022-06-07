package cz.patyk.invoicesystem_be.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ServiceCatalogDtoOut {
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
