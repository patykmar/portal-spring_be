package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
