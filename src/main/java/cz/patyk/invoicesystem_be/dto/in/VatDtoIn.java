package cz.patyk.invoicesystem_be.dto.in;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
