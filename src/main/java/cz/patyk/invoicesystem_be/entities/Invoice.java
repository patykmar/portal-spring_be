package cz.patyk.invoicesystem_be.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier", nullable = false)
    @ToString.Exclude
    private Company supplier;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscriber", nullable = false)
    @ToString.Exclude
    private Company subscriber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_type_id", nullable = false)
    @ToString.Exclude
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User userCreated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
    @ToString.Exclude
    private List<WorkInventory> workInventoryList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "invoice")
    @ToString.Exclude
    private List<InvoiceItem> invoiceItemList = new ArrayList<>();

    private String name;
    private int due;
    private Date invoiceCreated;
    private Date dueDate;
    private Date paymentDate;
    private String vs;
    private String ks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
