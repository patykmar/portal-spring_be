package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepository extends JpaRepository<Tariff, Long> {
}
