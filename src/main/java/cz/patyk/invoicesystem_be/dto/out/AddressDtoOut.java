package cz.patyk.invoicesystem_be.dto.out;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class AddressDtoOut extends RepresentationModel<AddressDtoOut> {
    private final Long id;
    private final CountryDto countryDto;
    private final Long country;
    private final String street;
    private final String city;
    private final String zipCode;
}

