package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.out.InvoiceItemDtoOut;
import cz.patyk.invoicesystem_be.dto.in.InvoiceItemDtoIn;
import cz.patyk.invoicesystem_be.entities.Invoice;
import cz.patyk.invoicesystem_be.entities.InvoiceItem;
import cz.patyk.invoicesystem_be.entities.Vat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {VatMapper.class})
public interface InvoiceItemMapper {

    @Mapping(target = "marginTotal", ignore = true)
    @Mapping(target = "priceIncMargin", ignore = true)
    @Mapping(target = "discountTotal", ignore = true)
    @Mapping(target = "priceIncMarginMinusDiscount", ignore = true)
    @Mapping(target = "priceIncMarginDiscountMultiVat", ignore = true)
    @Mapping(target = "priceIncMarginMultiVat", ignore = true)
    @Mapping(target = "totalPriceIncMarginDiscountVat", ignore = true)
    @Mapping(target = "totalPriceIncMarginVat", ignore = true)
    InvoiceItemDtoOut toDto(InvoiceItem invoiceItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vat", source = "vat")
    @Mapping(target = "invoice", source = "invoice")
    @Mapping(target = "name", source = "invoiceItemDtoIn.name")
    InvoiceItem toEntity(InvoiceItemDtoIn invoiceItemDtoIn, Vat vat, Invoice invoice);
}
