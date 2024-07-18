package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.InvoiceItemDto;
import cz.patyk.invoicesystem_be.entities.InvoiceItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface InvoiceItemMapper {
    @Mapping(target = "vatId", ignore = true)
    @Mapping(target = "invoiceId", ignore = true)
    InvoiceItemDto toDto(InvoiceItem invoiceItem);

    @Mapping(target = "vat", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    InvoiceItem toEntity(InvoiceItemDto invoiceItemDto);
}
