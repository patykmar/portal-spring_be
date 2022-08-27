package cz.patyk.invoicesystem_be.dto.out;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
public class SlaDtoOut extends RepresentationModel<SlaDtoOut> {
    private long id;
    private long tariffId;
    private long priorityId;
    private long ticketTypeId;
    private int reactionTime;
    private int resolvedTime;
    private int priceMultiplier;
}
