package cz.patyk.invoicesystem_be.convertor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cz.patyk.invoicesystem_be.constant.TestDtos;
import cz.patyk.invoicesystem_be.entities.Invoice;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceConvertorTest {

    @Autowired
    InvoiceConvertor invoiceConvertor;

    @Test
    void convertToInvoice() {
        Invoice invoice = invoiceConvertor.convertToInvoice(TestDtos.INVOICE_DTO_IN);
        Assertions.assertThat(invoice)
            .isNotNull();
    }

    @Test
    void generateVsTest() {
        var generateVs = invoiceConvertor.generateVs();
        Assertions.assertThat(generateVs)
            .isNotNull()
            .hasSize(10);
    }
}