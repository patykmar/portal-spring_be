package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
}
