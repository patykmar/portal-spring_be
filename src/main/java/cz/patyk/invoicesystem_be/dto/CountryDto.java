package cz.patyk.invoicesystem_be.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class CountryDto extends RepresentationModel<CountryDto> {
    @Positive
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String iso3166alpha3;

    @Override
    public String toString() {
        return String.format("%s %s", name, iso3166alpha3);
    }
}
