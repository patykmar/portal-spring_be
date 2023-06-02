package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.Ci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiRepository extends JpaRepository<Ci, Long> {
}
