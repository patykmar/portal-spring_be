package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PaymentTypeDto extends RepresentationModel<PaymentTypeDto> {
    private Long id;
    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull
    private boolean isDefault;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PaymentTypeDto that = (PaymentTypeDto) o;
        return isDefault == that.isDefault && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, isDefault);
    }
}
