package cz.patyk.invoicesystem_be.dto.in;

import cz.patyk.invoicesystem_be.dto.out.InvoiceItemDtoOut;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
    private String name;
    private String invoiceCreated;
    private String vs;
    private String ks;
    private List<WorkInventoryDtoIn> workInventories;
    private List<InvoiceItemDtoIn> invoiceItems;
}
