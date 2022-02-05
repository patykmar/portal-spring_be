package cz.patyk.invoicesystem_be.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CompanyDtoIn {
    private int id;
    private String name;
    private String description;
    private String companyId;
    private String vatNumber;
    private String accountNumber;
    private String iban;
    private Long addressId;
}
