package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.in.CiDtoIn;
import cz.patyk.invoicesystem_be.dto.out.CiDtoOut;
import cz.patyk.invoicesystem_be.entities.Ci;
import cz.patyk.invoicesystem_be.exceptions.ApplicationException;
import cz.patyk.invoicesystem_be.mapper.CiMapper;
import cz.patyk.invoicesystem_be.repositories.CiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CiService implements CrudService<CiDtoIn, CiDtoOut, Ci> {
    private final CiRepository ciRepository;
    private final CiMapper ciMapper;
    private final ErrorHandleService errorHandleService;

    @Override
    public List<CiDtoOut> getAll(Pageable pageable) {
        return ciRepository.findAll(pageable)
                .stream()
                .map(ciMapper::toDtoOut)
                .toList();
    }

    @Override
    public CiDtoOut getOne(Long id) {
        return ciMapper.toDtoOut(getOneEntity(id));
    }

    @Override
    public Ci getOneEntity(Long id) {
        return ciRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, ServiceConstants.CI_NOT_FOUND_MESSAGE));
    }

    @Override
    public CiDtoOut newItem(CiDtoIn dtoIn) {
        Ci ci = ciMapper.toEntity(dtoIn);
        return ciMapper.toDtoOut(ciRepository.save(ci));
    }

    @Override
    public CiDtoOut editItem(CiDtoIn dtoIn, Long id) {
        checkIfExist(id);
        Ci ci = ciMapper.toEntity(dtoIn);
        ci.setId(id);
        return ciMapper.toDtoOut(ciRepository.save(ci));
    }

    @Override
    public void deleteItem(Long id) {
        checkIfExist(id);
        ciRepository.deleteById(id);
    }

    private void checkIfExist(Long id) throws ApplicationException {
        if (!ciRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, ServiceConstants.CI_NOT_FOUND_MESSAGE);
        }
    }
}
