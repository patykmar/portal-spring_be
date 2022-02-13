package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddressDto {
    private Long id;
    private CountryDto countryDto;
    private Long countryId;
    private String street;
    private String city;
    private String zipCode;
}
