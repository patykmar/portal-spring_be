package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.EmailNotificationCi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailNotificationCiRepository extends JpaRepository<EmailNotificationCi, Long> {
}
