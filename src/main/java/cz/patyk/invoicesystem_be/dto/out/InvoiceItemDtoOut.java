package cz.patyk.invoicesystem_be.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
public class InvoiceItemDtoOut {
    private Long id;
    private VatDtoOut vat;
    private String name;
    private String price;
    private Integer margin;
    private Integer discount;
    private Float unitCount;
    private int marginTotal;
    private int priceIncMargin;
    private int discountTotal;
    private int priceIncMarginMinusDiscount;
    private int priceIncMarginDiscountMultiVat;
    private int priceIncMarginMultiVat;
    private BigInteger totalPriceIncMarginDiscountVat;
    private BigInteger totalPriceIncMarginVat;
}
