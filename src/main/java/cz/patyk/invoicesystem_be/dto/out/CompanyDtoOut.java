package cz.patyk.invoicesystem_be.dto.out;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class CompanyDtoOut extends RepresentationModel<CompanyDtoOut> {
    private final Long id;
    private final String name;
    private final String description;
    private final String companyId;
    private final String vatNumber;
    private final String accountNumber;
    private final String iban;
    private final Long address;
    private final AddressDtoOut addressDtoOut;
    private final int created;
    private final int modify;
}