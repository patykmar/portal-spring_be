package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.InfluencingTicket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfluencingTicketRepository extends JpaRepository<InfluencingTicket, Long> {
    List<InfluencingTicket> findAllByIsForPriorityIsTrue(Pageable pageable);

    List<InfluencingTicket> findAllByIsForImpactIsTrue(Pageable pageable);
}
