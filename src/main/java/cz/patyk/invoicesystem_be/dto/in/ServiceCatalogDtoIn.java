package cz.patyk.invoicesystem_be.dto.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceCatalogDtoIn {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long vat;
    private int estimateTimeDelivery;
    private int estimateTimeReaction;
    private boolean isDisable;
}
