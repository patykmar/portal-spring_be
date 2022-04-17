package cz.patyk.invoicesystem_be.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
@Setter
public class QueueDto extends RepresentationModel<QueueDto> {
    private Long id;
    private String name;
}
