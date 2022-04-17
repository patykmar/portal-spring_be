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
public class TariffDtoOut extends RepresentationModel<TariffDtoOut> {
    private Long id;
    private VatDtoOut vatDto;
    private Long vat;
    private String name;
    private Long price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TariffDtoOut that = (TariffDtoOut) o;
        return Objects.equals(id, that.id) && Objects.equals(vatDto, that.vatDto) && Objects.equals(vat, that.vat) && Objects.equals(name, that.name) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, vatDto, vat, name, price);
    }
}
