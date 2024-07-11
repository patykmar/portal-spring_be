package cz.patyk.invoicesystem_be.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Slf4j
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vat implements Serializable {

    public static final int MULTIPLIER_CONST = 100;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isDefault;
    private String name;
    private int percent;
    private int multiplier;

    @PrePersist
    @PreUpdate
    public void calculateMultiplier() {
        if (multiplier <= 0) {
            int multiplierLocal = percent + MULTIPLIER_CONST;
            log.warn("Multiplier is not set. Current value {} will be replace to {}", multiplier, multiplierLocal);
            setMultiplier(multiplierLocal);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Vat vat = (Vat) o;
        return Objects.equals(id, vat.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
