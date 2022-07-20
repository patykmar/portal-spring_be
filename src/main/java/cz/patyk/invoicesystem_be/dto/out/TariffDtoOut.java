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
public class TariffDtoOut extends RepresentationModel<TariffDtoOut> {
    private Long id;
    private VatDtoOut vatDto;
    private Long vat;
    private String name;
    private Long price;
}
