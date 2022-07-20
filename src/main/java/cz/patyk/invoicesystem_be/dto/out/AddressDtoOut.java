package cz.patyk.invoicesystem_be.dto.out;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddressDtoOut extends RepresentationModel<AddressDtoOut> {
    private Long id;
    private CountryDto countryDto;
    private Long country;
    private String street;
    private String city;
    private String zipCode;
}

