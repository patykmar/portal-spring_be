package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.InvoiceDto;
import cz.patyk.invoicesystem_be.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice toEntity(InvoiceDto invoiceDto);

    InvoiceDto toDto(Invoice invoice);
}
