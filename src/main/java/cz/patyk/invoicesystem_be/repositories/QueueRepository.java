package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<Queue, Long> {
}
