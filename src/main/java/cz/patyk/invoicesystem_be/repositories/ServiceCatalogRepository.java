package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.ServiceCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCatalogRepository extends JpaRepository<ServiceCatalog, Long> {
}
