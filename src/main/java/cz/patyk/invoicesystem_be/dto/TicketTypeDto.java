package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TicketTypeDto extends RepresentationModel<TicketTypeDto> {
    private Long id;
    private String name;
    private String abbreviation;
    private boolean isDisable;
    private float coefficientPrice;
    private float coefficientTime;
}
