package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.Ci;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiRepository extends JpaRepository<Ci, Long> {
}
