package cz.patyk.invoicesystem_be.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class InvoiceItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    @ToString.Exclude
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vat_id", nullable = false)
    @ToString.Exclude
    private Vat vat;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InvoiceItem that = (InvoiceItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
