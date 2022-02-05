package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
