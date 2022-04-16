package cz.patyk.invoicesystem_be.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TariffDtoOut {
    private Long id;
    private VatDtoOut vatDto;
    private Long vat;
    private String name;
    private Long price;
}
