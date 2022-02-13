package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EmailNotificationCiDto {
    private Long id;
    private String emailAddress;
    private Long ciId;
}
