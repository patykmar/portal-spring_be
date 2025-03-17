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
public class InfluencingTicketDto extends RepresentationModel<InfluencingTicketDto> {
    private Long id;
    private String name;
    private boolean isForPriority;
    private boolean isForImpact;
    private float coefficientPrice;
    private float coefficientTime;
}
