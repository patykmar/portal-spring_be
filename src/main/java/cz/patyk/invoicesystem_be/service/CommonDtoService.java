package cz.patyk.invoicesystem_be.service;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CommonDtoService<R, I> {
    List<R> getAll(Pageable pageable);
    R getOne(Long id);
    R newItem(I dtoIn);
    R editItem(I dtoIn, Long id);
    void deleteItem(Long id);
}
