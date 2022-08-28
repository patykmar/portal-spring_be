package cz.patyk.invoicesystem_be.dto.out;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class PaymentTypeDtoOut extends RepresentationModel<PaymentTypeDtoOut> {
    private Long id;
    private String name;
    private boolean isDefault;
}
