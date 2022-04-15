package cz.patyk.invoicesystem_be.dto.out;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class AddressDtoOut extends RepresentationModel<AddressDtoOut> {
    private Long id;
    private CountryDto countryDto;
    private Long country;
    private String street;
    private String city;
    private String zipCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AddressDtoOut that = (AddressDtoOut) o;
        return Objects.equals(id, that.id) && Objects.equals(countryDto, that.countryDto) && Objects.equals(country, that.country) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, countryDto, country, street, city, zipCode);
    }
}
