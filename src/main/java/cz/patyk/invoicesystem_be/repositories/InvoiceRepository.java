package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
