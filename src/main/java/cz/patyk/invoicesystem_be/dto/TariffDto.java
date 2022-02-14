package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TariffDto {
    private Long id;
    private Long vatId;
    private String name;
    private Long price;
}
