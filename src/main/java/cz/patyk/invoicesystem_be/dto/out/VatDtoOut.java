package cz.patyk.invoicesystem_be.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class VatDtoOut extends RepresentationModel<VatDtoOut> {
    private Long id;
    private boolean isDefault;
    private String name;
    private int percent;
    private int multiplier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VatDtoOut vatDto = (VatDtoOut) o;
        return isDefault == vatDto.isDefault && percent == vatDto.percent && multiplier == vatDto.multiplier && Objects.equals(id, vatDto.id) && Objects.equals(name, vatDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, isDefault, name, percent, multiplier);
    }
}