package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
public class InvoiceItemDto {
    private Long id;
    private Long invoiceId;
    private Long vatId;
    private String name;
    private int price;
    private int margin;
    private int marginTotal;
    private int priceIncMargin;
    private int discount;
    private int discountTotal;
    private int priceIncMarginMinusDiscount;
    private int priceIncMarginDiscountMultiVat;
    private int priceIncMarginMultiVat;
    private int unitCount;
    private BigInteger totalPriceIncMarginDiscountVat;
    private BigInteger totalPriceIncMarginVat;
}
