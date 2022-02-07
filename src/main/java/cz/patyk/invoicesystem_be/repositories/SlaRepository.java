package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.Sla;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlaRepository extends JpaRepository<Sla, Long> {
}
