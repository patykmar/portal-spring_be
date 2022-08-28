package cz.patyk.invoicesystem_be.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompanyDtoOut extends RepresentationModel<CompanyDtoOut> {
    private Long id;
    private String name;
    private String description;
    private String companyId;
    private String vatNumber;
    private String accountNumber;
    private String iban;
    private Long address;
    private AddressDtoOut addressDtoOut;
    private int created;
    private int modify;
}