package cz.patyk.invoicesystem_be.dto.in;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class VatDtoIn {
    private Long id;
    private boolean isDefault;
    private String name;
    @Min(0)
    @Max(100)
    private int percent;
}
