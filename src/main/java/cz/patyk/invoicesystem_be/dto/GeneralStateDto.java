package cz.patyk.invoicesystem_be.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Getter
@Setter
@Builder
public class GeneralStateDto extends RepresentationModel<GeneralStateDto> {
    private Long id;
    private String name;
    private boolean isForTicket;
    private boolean isForCi;
    private boolean isForCloseState;
    private float coefficientPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GeneralStateDto that = (GeneralStateDto) o;
        return isForTicket == that.isForTicket && isForCi == that.isForCi && isForCloseState == that.isForCloseState && Float.compare(that.coefficientPrice, coefficientPrice) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, isForTicket, isForCi, isForCloseState, coefficientPrice);
    }
}
