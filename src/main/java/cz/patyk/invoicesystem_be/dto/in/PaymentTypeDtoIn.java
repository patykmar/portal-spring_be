package cz.patyk.invoicesystem_be.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentTypeDtoIn {
    private Long id;
    @NotNull(message = "Name is mandatory")
    private String name;
    @NotNull
    private boolean isDefault;
}
