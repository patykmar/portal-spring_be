package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.Vat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VatRepository extends JpaRepository<Vat, Integer> {
}
