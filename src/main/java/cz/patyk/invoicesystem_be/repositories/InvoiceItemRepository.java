package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
