package cz.patyk.invoicesystem_be.convertor;

import cz.patyk.invoicesystem_be.dto.out.InvoiceDtoOut;
import cz.patyk.invoicesystem_be.entities.InvoiceItem;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import cz.patyk.invoicesystem_be.dto.in.InvoiceDtoIn;
import cz.patyk.invoicesystem_be.entities.Invoice;
import cz.patyk.invoicesystem_be.mapper.InvoiceMapper;
import cz.patyk.invoicesystem_be.service.CompanyService;
import cz.patyk.invoicesystem_be.service.PaymentTypeService;
import cz.patyk.invoicesystem_be.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceConvertor implements CrudConvertor<InvoiceDtoIn, InvoiceDtoOut, Invoice> {
    private final CompanyService companyService;
    private final InvoiceMapper invoiceMapper;
    private final PaymentTypeService paymentTypeService;
    private final UserService userService;
    private final InvoiceItemConverter invoiceItemConvertor;

    @Override
    public Invoice inputToEntity(InvoiceDtoIn invoiceDtoIn) {
        Invoice invoice = invoiceMapper.toEntity(invoiceDtoIn);
        var supplier = companyService.getOneEntity(invoiceDtoIn.getSupplier());
        var subscriber = companyService.getOneEntity(invoiceDtoIn.getSubscriber());
        var paymentType = paymentTypeService.getOneEntity(invoiceDtoIn.getPaymentType());

        invoice.setSubscriber(subscriber);
        invoice.setSupplier(supplier);
        invoice.setPaymentType(paymentType);

        if (Objects.isNull(invoiceDtoIn.getInvoiceCreated())) {
            invoice.setInvoiceCreated(new Date());
        }
        if (Objects.isNull(invoiceDtoIn.getUserCreated())) {
            invoice.setUserCreated(userService.getOneEntity(1L));
        }
        if (Objects.isNull(invoiceDtoIn.getVs())) {
            invoice.setVs(generateVs());
        }

        var name = String.format("%s: %s (%s) -> %s (%s)", invoice.getVs(), supplier.getDescription(), supplier.getCompanyId(), subscriber.getDescription(),
                subscriber.getCompanyId()
        );
        invoice.setName(name);

        //TODO: move time zone and KS to application parameter
        var dueDate = ZonedDateTime.ofInstant(invoice.getInvoiceCreated().toInstant(), ZoneId.of("Europe/Prague"))
                .plusDays(invoice.getDue()).toInstant();
        invoice.setDueDate(Date.from(dueDate));

        if (Objects.isNull(invoiceDtoIn.getKs())) {
            invoice.setKs("0308");
        }

        var listOfInvoiceItems = invoiceDtoIn.getInvoiceItems().stream()
                .map(invoiceItemDtoIn -> invoiceItemConvertor.inputToEntity(invoiceItemDtoIn, invoice))
                .toList();
        invoice.setInvoiceItemList(listOfInvoiceItems);

        return invoice;
    }

    public String generateVs() {
        var year = ZonedDateTime.now().getYear();
        return String.format("%s%06d", year, RandomUtils.secure().randomInt(100, 999999));
    }

    @Override
    public InvoiceDtoOut entityToDto(Invoice entity) {
        return invoiceMapper.toDtoOut(entity);
    }
}
