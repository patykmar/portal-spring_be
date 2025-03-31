package cz.patyk.invoicesystem_be.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InvoiceItemDtoIn {
    @NotNull
    private Long vatId;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    private Integer margin;
    private Integer discount;
    @NotNull
    private Float unitCount;
}
