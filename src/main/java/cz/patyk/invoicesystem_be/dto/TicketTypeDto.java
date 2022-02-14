package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TicketTypeDto {
    private Long id;
    private String name;
    private String abbreviation;
    private boolean isDisable;
    private float coefficientPrice;
    private float coefficientTime;
}
