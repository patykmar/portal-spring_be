package cz.patyk.invoicesystem_be.dto.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SlaDtoIn {
    private Long id;
    private Long tariffId;
    private Long priorityId;
    private Long ticketTypeId;
    private int reactionTime;
    private int resolvedTime;
    private int priceMultiplier;
}
