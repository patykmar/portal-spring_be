package cz.patyk.invoicesystem_be.convertor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cz.patyk.invoicesystem_be.constant.TestDtos;
import cz.patyk.invoicesystem_be.entities.Invoice;

@SpringBootTest
class InvoiceConvertorTest {

    @Autowired
    InvoiceConvertor invoiceConvertor;

    @Test
    void convertToInvoice() {
        Invoice invoice = invoiceConvertor.inputToEntity(TestDtos.INVOICE_DTO_IN);
        Assertions.assertThat(invoice)
                .isNotNull()
                .hasNoNullFieldsOrPropertiesExcept("paymentDate");

    }

    @Test
    void generateVsTest() {
        var generateVs = invoiceConvertor.generateVs();
        Assertions.assertThat(generateVs)
                .isNotNull()
                .hasSize(10);
    }
}