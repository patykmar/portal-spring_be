package cz.patyk.invoicesystem_be.dto.out;

import cz.patyk.invoicesystem_be.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CompanyDtoOut {
    private int id;
    private String name;
    private String description;
    private String companyId;
    private String vatNumber;
    private String accountNumber;
    private String iban;
    private AddressDto addressDto;
    private Date created;
    private Date modify;
}