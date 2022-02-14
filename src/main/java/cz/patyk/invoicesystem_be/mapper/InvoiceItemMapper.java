package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.InvoiceItemDto;
import cz.patyk.invoicesystem_be.entities.InvoiceItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceItemMapper {
    InvoiceItemDto toDto(InvoiceItem invoiceItem);

    InvoiceItem toEntity(InvoiceItemDto invoiceItemDto);
}
