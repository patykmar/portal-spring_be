package cz.patyk.invoicesystem_be.dto.out;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SlaDtoOut {
    private long id;
    private long tariffId;
    private long priorityId;
    private long ticketTypeId;
    private int reactionTime;
    private int resolvedTime;
    private int priceMultiplier;
}
