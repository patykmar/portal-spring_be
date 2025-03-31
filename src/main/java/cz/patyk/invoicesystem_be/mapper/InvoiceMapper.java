package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.InvoiceDtoIn;
import cz.patyk.invoicesystem_be.dto.out.InvoiceDtoOut;
import cz.patyk.invoicesystem_be.entities.Invoice;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {CompanyMapper.class, PaymentTypeMapper.class, UserMapper.class, WorkInventoryMapper.class, InvoiceItemMapper.class})
public interface InvoiceMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "subscriber", ignore = true)
    @Mapping(target = "paymentType", ignore = true)
    @Mapping(target = "userCreated", ignore = true)
    @Mapping(target = "invoiceCreated", ignore = true)
    @Mapping(target = "workInventoryList", ignore = true)
    @Mapping(target = "invoiceItemList", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "dueDate", ignore = true)
    @Mapping(target = "paymentDate", ignore = true)
    Invoice toEntity(InvoiceDtoIn invoiceDtoIn);

    @Mapping(target = "links", ignore = true)
    InvoiceDtoOut toDtoOut(Invoice invoice);
}
