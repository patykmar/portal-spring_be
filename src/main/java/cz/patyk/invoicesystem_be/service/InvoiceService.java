package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.convertor.InvoiceConvertor;
import cz.patyk.invoicesystem_be.dto.out.InvoiceDtoOut;
import cz.patyk.invoicesystem_be.dto.in.InvoiceDtoIn;
import cz.patyk.invoicesystem_be.entities.Invoice;
import cz.patyk.invoicesystem_be.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService implements CrudService<InvoiceDtoIn, InvoiceDtoOut, Invoice> {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceConvertor invoiceConvertor;
    private final ErrorHandleService errorHandleService;

    @Override
    public List<InvoiceDtoOut> getAll(Pageable pageable) {
        return invoiceRepository.findAll(pageable)
                .stream()
                .map(invoiceConvertor::entityToDto)
                .toList();
    }

    @Override
    public InvoiceDtoOut getOne(Long id) {
        return invoiceConvertor.entityToDto(getOneEntity(id));
    }

    @Override
    public Invoice getOneEntity(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, ServiceConstants.INVOICE_NOT_FOUND_MESSAGE));
    }

    @Override
    public InvoiceDtoOut newItem(InvoiceDtoIn dtoIn) {
        var invoiceFromUser = invoiceConvertor.inputToEntity(dtoIn);
        return invoiceConvertor.entityToDto(invoiceRepository.save(invoiceFromUser));
    }

    @Override
    public InvoiceDtoOut editItem(InvoiceDtoIn dtoIn, Long id) {
        var invoiceById = getOneEntity(id);
        var invoiceFromUser = invoiceConvertor.inputToEntity(dtoIn);
        invoiceFromUser.setId(invoiceById.getId());
        return invoiceConvertor.entityToDto(invoiceRepository.save(invoiceFromUser));
    }

    @Override
    public void deleteItem(Long id) {
        var invoice = getOneEntity(id);
        invoiceRepository.delete(invoice);
    }
}
