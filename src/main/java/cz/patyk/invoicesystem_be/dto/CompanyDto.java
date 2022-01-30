package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private int id;
    private int country;
    private String name;
    private String description;
    private String companyId;
    private String vatNumber;
    private Date created;
    private Date modify;
    private String street;
    private String city;
    private String zipCode;
    private String accountNumber;
    private String iban;
}