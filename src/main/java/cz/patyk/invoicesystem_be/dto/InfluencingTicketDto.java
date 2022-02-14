package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InfluencingTicketDto {
    private Long id;
    private String name;
    private boolean isForPriority;
    private boolean isForImpact;
    private float coefficientPrice;
    private float coefficientTime;
}
