package cz.patyk.invoicesystem_be.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CompanyDtoIn {
    private Long id;
    private String name;
    private String description;
    private String companyId;
    private String vatNumber;
    private String accountNumber;
    private String iban;
    private Long address;
}
