package cz.patyk.invoicesystem_be.dto.out;

import cz.patyk.invoicesystem_be.dto.VatDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TariffDtoOut {
    private Long id;
    private VatDto vatDto;
    private Long vat;
    private String name;
    private Long price;
}
