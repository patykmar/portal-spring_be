package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GeneralLogDto {
    private Long id;
    private Long ciId;
    private Long ticketId;
    private Long userId;
    private String body;
}