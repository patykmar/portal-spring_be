package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
