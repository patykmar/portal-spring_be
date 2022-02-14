package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
