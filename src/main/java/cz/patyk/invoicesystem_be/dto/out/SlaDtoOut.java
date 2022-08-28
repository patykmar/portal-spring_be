package cz.patyk.invoicesystem_be.dto.out;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;


@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class SlaDtoOut extends RepresentationModel<SlaDtoOut> {
    private final long id;
    private final long tariffId;
    private final long priorityId;
    private final long ticketTypeId;
    private final int reactionTime;
    private final int resolvedTime;
    private final int priceMultiplier;
}
