package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VatDto {
    private Long id;
    private boolean isDefault;
    private String name;
    private int percent;
    private int multiplier;
}
