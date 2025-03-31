package cz.patyk.invoicesystem_be.dto.out;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class WorkInventoryDtoOut extends RepresentationModel<WorkInventoryDtoOut> {
    private Long id;
    private TariffDtoOut tariff;
    private CompanyDtoOut company;
    private UserDtoOut user;
    private Long invoiceId;
    private String description;
    private String workStart;
    private String workEnd;
    private float workDuration;
}
