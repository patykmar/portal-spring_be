package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SlaDto {
    private Long id;
    private int reactionTime;
    private int resolvedTime;
    private int priceMultiplier;
    private Long tariffId;
    private Long priorityId;
    private Long ticketTypeId;
}
