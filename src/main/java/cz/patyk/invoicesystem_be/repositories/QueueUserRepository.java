package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.QueueUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueUserRepository extends JpaRepository<QueueUser, Long> {
}
