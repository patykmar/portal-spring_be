package cz.patyk.invoicesystem_be.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EmailNotificationCiDtoIn {
    private Long id;
    private String emailAddress;
    private Long ciId;
}
