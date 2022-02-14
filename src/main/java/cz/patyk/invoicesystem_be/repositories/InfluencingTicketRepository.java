package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.InfluencingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluencingTicketRepository extends JpaRepository<InfluencingTicket, Long> {
}
