package cz.patyk.invoicesystem_be.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Builder
@Getter
@Setter
public class CountryDto extends RepresentationModel<CountryDto> {
    @Positive
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String iso3166alpha3;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CountryDto that = (CountryDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(iso3166alpha3, that.iso3166alpha3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, iso3166alpha3);
    }
}
