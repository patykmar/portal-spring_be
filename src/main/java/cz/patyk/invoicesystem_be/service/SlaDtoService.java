package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.in.SlaDtoIn;
import cz.patyk.invoicesystem_be.dto.out.SlaDtoOut;
import cz.patyk.invoicesystem_be.entities.Sla;
import cz.patyk.invoicesystem_be.exceptions.ApplicationException;
import cz.patyk.invoicesystem_be.mapper.SlaMapper;
import cz.patyk.invoicesystem_be.repositories.SlaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlaDtoService implements CommonDtoService<SlaDtoOut, SlaDtoIn>, CommonEntityService<Sla> {
    private final SlaMapper slaMapper;
    private final SlaRepository slaRepository;
    private final ErrorHandleService errorHandleService;

    @Override
    public List<SlaDtoOut> getAll(Pageable pageable) {
        return slaRepository.findAll(pageable)
                .stream()
                .map(slaMapper::toDtoOut)
                .toList();
    }

    @Override
    public SlaDtoOut getOne(Long id) {
        return slaMapper.toDtoOut(getOneEntity(id));
    }

    @Override
    public SlaDtoOut newItem(SlaDtoIn dtoIn) {
        Sla sla = slaMapper.toEntity(dtoIn);
        return slaMapper.toDtoOut(slaRepository.save(sla));
    }

    @Override
    public SlaDtoOut editItem(SlaDtoIn dtoIn, Long id) {
        checkIfExist(id);
        Sla sla = slaMapper.toEntity(dtoIn);
        sla.setId(id);
        return slaMapper.toDtoOut(slaRepository.save(sla));
    }

    @Override
    public void deleteItem(Long id) {
        checkIfExist(id);
        slaRepository.deleteById(id);
    }

    private void checkIfExist(Long id) throws ApplicationException {
        if (!slaRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, ServiceConstants.SLA_NOT_FOUND);
        }
    }

    @Override
    public Sla getOneEntity(Long id) {
        return slaRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, ServiceConstants.SLA_NOT_FOUND));
    }
}
