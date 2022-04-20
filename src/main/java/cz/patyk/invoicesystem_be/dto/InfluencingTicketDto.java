package cz.patyk.invoicesystem_be.dto;

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
public class InfluencingTicketDto extends RepresentationModel<InfluencingTicketDto> {
    private Long id;
    private String name;
    private boolean isForPriority;
    private boolean isForImpact;
    private float coefficientPrice;
    private float coefficientTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InfluencingTicketDto that = (InfluencingTicketDto) o;
        return isForPriority == that.isForPriority && isForImpact == that.isForImpact && Float.compare(that.coefficientPrice, coefficientPrice) == 0 && Float.compare(that.coefficientTime, coefficientTime) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, isForPriority, isForImpact, coefficientPrice, coefficientTime);
    }
}
