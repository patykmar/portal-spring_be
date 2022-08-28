package cz.patyk.invoicesystem_be.dto.out;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;


@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class TariffDtoOut extends RepresentationModel<TariffDtoOut> {
    private final Long id;
    private final VatDtoOut vatDto;
    private final Long vat;
    private final String name;
    private final Long price;
}
