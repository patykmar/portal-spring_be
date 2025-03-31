package cz.patyk.invoicesystem_be.dto.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkInventoryDtoIn {
    private Long id;
    private Long tariffId;
    private Long ciId;
    private Long companyId;
    private String description;
    private long workStart;
    private long workEnd;
    private float workDuration;
}
