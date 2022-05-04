package cz.patyk.invoicesystem_be.dto.in;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class PaymentTypeDtoIn {
    private Long id;

    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull
    private boolean isDefault;
}
