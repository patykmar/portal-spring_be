package cz.patyk.invoicesystem_be.dto;

import cz.patyk.invoicesystem_be.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private int id;
    private Company supplier;
    private Company subscriber;
    private PaymentTypeDto paymentType;
    private User userCreated;
    private List<WorkInventory> workInventoryList = new ArrayList<>();
    private List<InvoiceItem> invoiceItemList = new ArrayList<>();
    private String name;
    private int due;
    private Date invoiceCreated;
    private Date dueDate;
    private Date paymentDate;
    private String vs;
    private String ks;

}
