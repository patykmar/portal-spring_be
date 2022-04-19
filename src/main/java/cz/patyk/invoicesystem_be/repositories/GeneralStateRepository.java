package cz.patyk.invoicesystem_be.repositories;

import cz.patyk.invoicesystem_be.entities.GeneralState;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneralStateRepository extends JpaRepository<GeneralState, Long> {
    List<GeneralState> findAllByIsForTicketIsTrue(Pageable pageable);
    List<GeneralState> findAllByIsForCiTrue(Pageable pageable);
    List<GeneralState> findAllByIsForCloseStateTrue(Pageable pageable);
}
