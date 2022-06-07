package cz.patyk.invoicesystem_be.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCatalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text", nullable = false)
    private String description;
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vat_id", nullable = false)
    @ToString.Exclude
    private Vat vat;

    @Column(nullable = false)
    private int estimateTimeDelivery;

    @Column(nullable = false)
    private int estimateTimeReaction;

    @Column(columnDefinition = "boolean default false")
    private boolean isDisable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceCatalog that = (ServiceCatalog) o;
        return estimateTimeDelivery == that.estimateTimeDelivery &&
                estimateTimeReaction == that.estimateTimeReaction &&
                isDisable == that.isDisable &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(vat, that.vat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, vat, estimateTimeDelivery, estimateTimeReaction, isDisable);
    }
}
