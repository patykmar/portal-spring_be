package cz.patyk.invoicesystem_be.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VatDtoOut extends RepresentationModel<VatDtoOut> {
    private Long id;
    private boolean isDefault;
    private String name;
    private int percent;
    private int multiplier;
}