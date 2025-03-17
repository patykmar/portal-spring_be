package cz.patyk.invoicesystem_be.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceDtoIn {
    @NotNull
    private Long supplier;
    @NotNull
    private Long subscriber;
    @NotNull
    private Long paymentType;
    private Long userCreated;
    @NotNull
    private Integer due;
    private String invoiceCreated;
    private String vs;
    private String ks;
}
