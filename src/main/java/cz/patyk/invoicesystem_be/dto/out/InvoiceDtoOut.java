package cz.patyk.invoicesystem_be.dto.out;

import cz.patyk.invoicesystem_be.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InvoiceDtoOut extends RepresentationModel<InvoiceDtoOut> {
    private int id;
    private CompanyDtoOut supplier;
    private CompanyDtoOut subscriber;
    private PaymentTypeDtoOut paymentType;
    private User userCreated;
    private List<WorkInventoryDtoOut> workInventoryList = new ArrayList<>();
    private List<InvoiceItemDtoOut> invoiceItemList = new ArrayList<>();
    private String name;
    private int due;
    private Date invoiceCreated;
    private Date dueDate;
    private Date paymentDate;
    private String vs;
    private String ks;

}
