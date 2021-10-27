package cz.patyk.invoicesystem_be.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "ci_id", nullable = false)
    @ToString.Exclude
    private Ci ci;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "queue_user_id", nullable = false)
    @ToString.Exclude
    private QueueUser queueUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_created_id", nullable = false)
    @ToString.Exclude
    private User userCreated;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_resolved_id")
    @ToString.Exclude
    private User userResolved;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "parent_ticket_id")
    @ToString.Exclude
    private Ticket parentTicket;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "work_inventory_id")
    @ToString.Exclude
    private WorkInventory workInventory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "ticket_close_state_id")
    @ToString.Exclude
    private GeneralState ticketCloseState;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "ticket_state_id", nullable = false)
    @ToString.Exclude
    private GeneralState ticketState;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "ticket_type_id", nullable = false)
    @ToString.Exclude
    private TicketType ticketType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "priority_id", nullable = false)
    @ToString.Exclude
    private InfluencingTicket priority;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "impact_id", nullable = false)
    @ToString.Exclude
    private InfluencingTicket impact;

    @JoinColumn(nullable = false)
    private String descriptionTitle;

    @JoinColumn(nullable = false)
    private String descriptionBody;

    private String closedNotes;
    private Date closedDatetime;
    private Date reactionDatetime;
    private Date resolveDatetime;
    private Date createdDatetime;

    @JoinColumn(name = "to_string", nullable = false)
    private String toString;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}