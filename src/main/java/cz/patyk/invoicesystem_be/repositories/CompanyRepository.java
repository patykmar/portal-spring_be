package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
