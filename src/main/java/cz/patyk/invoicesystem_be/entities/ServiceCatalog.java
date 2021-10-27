package cz.patyk.invoicesystem_be.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ServiceCatalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(columnDefinition = "text", nullable = false)
    private String description;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vat_id", nullable = false)
    @ToString.Exclude
    private Vat vat;
    private int estimateTimeDelivery;
    private int estimateTimeReaction;
    private boolean isDisable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ServiceCatalog that = (ServiceCatalog) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
