package cz.patyk.invoicesystem_be.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Builder
@Getter
@Setter
public class QueueDto extends RepresentationModel<QueueDto> {
    private Long id;
    @NonNull
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QueueDto queueDto = (QueueDto) o;
        return Objects.equals(id, queueDto.id) && Objects.equals(name, queueDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name);
    }
}
