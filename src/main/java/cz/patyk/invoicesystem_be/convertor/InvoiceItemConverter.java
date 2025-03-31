package cz.patyk.invoicesystem_be.convertor;

import cz.patyk.invoicesystem_be.dto.in.InvoiceItemDtoIn;
import cz.patyk.invoicesystem_be.dto.out.InvoiceItemDtoOut;
import cz.patyk.invoicesystem_be.entities.Invoice;
import cz.patyk.invoicesystem_be.entities.InvoiceItem;
import cz.patyk.invoicesystem_be.mapper.InvoiceItemMapper;
import cz.patyk.invoicesystem_be.service.VatService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceItemConverter implements CrudConvertor<InvoiceItemDtoIn, InvoiceItemDtoOut, InvoiceItem> {
    private final InvoiceItemMapper invoiceItemMapper;
    private final VatService vatService;

    @Override
    public InvoiceItem inputToEntity(InvoiceItemDtoIn userInput) {
        throw new NotImplementedException(
                "This method should not be used, use inputToEntity(InvoiceItemDtoIn userInput, Invoice invoice)");
    }

    public InvoiceItem inputToEntity(InvoiceItemDtoIn userInput, Invoice invoice) {
        var vat = vatService.getOneEntity(userInput.getVatId());
        return invoiceItemMapper.toEntity(userInput, vat, invoice);
    }

    @Override
    public InvoiceItemDtoOut entityToDto(InvoiceItem entity) {
        return invoiceItemMapper.toDto(entity);
    }
}
