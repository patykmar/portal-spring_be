package cz.patyk.invoicesystem_be.dto.mapper;

import cz.patyk.invoicesystem_be.dto.InvoiceDto;
import cz.patyk.invoicesystem_be.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface InvoiceMapper {
    Invoice toEntity(InvoiceDto source);
    InvoiceDto toDto(Invoice source);
}
