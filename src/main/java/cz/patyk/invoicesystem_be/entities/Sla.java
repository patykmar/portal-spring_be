package cz.patyk.invoicesystem_be.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Sla implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int reactionTime;

    @Column(nullable = false)
    private int resolvedTime;

    @Column(nullable = false)
    private int priceMultiplier;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tariff_id", nullable = false)
    @ToString.Exclude
    private Tariff tariff;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "priority_id", nullable = false)
    @ToString.Exclude
    private InfluencingTicket priority;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticket_type_id", nullable = false)
    @ToString.Exclude
    private TicketType ticketType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sla sla = (Sla) o;
        return id != null && Objects.equals(id, sla.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
