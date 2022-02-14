package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ServiceCatalogDto {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long vatId;
    private int estimateTimeDelivery;
    private int estimateTimeReaction;
    private boolean isDisable;
}
