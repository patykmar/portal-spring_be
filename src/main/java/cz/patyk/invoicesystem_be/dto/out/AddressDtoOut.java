package cz.patyk.invoicesystem_be.dto.out;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddressDtoOut {
    private Long id;
    private CountryDto countryDto;
    private Long country;
    private String street;
    private String city;
    private String zipCode;
}
