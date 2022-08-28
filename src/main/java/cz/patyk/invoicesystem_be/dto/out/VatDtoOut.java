package cz.patyk.invoicesystem_be.dto.out;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class VatDtoOut extends RepresentationModel<VatDtoOut> {
    private final Long id;
    private final boolean isDefault;
    private final String name;
    private final int percent;
    private final int multiplier;
}