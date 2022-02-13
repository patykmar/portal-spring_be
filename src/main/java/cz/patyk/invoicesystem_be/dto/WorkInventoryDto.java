package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class WorkInventoryDto {
    private Long id;
    private Long tariffId;
    private Long companyId;
    private Long userId;
    private Long invoiceId;
    private String description;
    private Date workStart;
    private Date workEnd;
    private float workDuration;
}
