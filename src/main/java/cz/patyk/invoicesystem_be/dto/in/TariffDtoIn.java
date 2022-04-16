package cz.patyk.invoicesystem_be.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TariffDtoIn {
    private Long id;
    private Long vat;
    private String name;
    private Long price;
}
