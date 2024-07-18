package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.InvoiceDto;
import cz.patyk.invoicesystem_be.entities.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface InvoiceMapper {
    @Mapping(target = "paymentType.isDefault", source = "paymentType.default")
    Invoice toEntity(InvoiceDto invoiceDto);

    @Mapping(target = "paymentType.isDefault", source = "paymentType.default")
    InvoiceDto toDto(Invoice invoice);
}
