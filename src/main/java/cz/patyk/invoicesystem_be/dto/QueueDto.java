package cz.patyk.invoicesystem_be.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class QueueDto extends RepresentationModel<QueueDto> {
    private Long id;
    private String name;
}
