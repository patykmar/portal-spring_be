package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}
