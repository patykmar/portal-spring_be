package cz.patyk.invoicesystem_be.dto.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueueDtoIn {
    private Long id;
    private String name;
}
