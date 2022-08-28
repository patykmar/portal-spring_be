package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.in.TariffDtoIn;
import cz.patyk.invoicesystem_be.dto.out.TariffDtoOut;
import cz.patyk.invoicesystem_be.entities.Tariff;
import cz.patyk.invoicesystem_be.mapper.TariffMapper;
import cz.patyk.invoicesystem_be.repositories.TariffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.patyk.invoicesystem_be.service.ServiceConstants.TARIFF_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class TariffService implements CrudService<TariffDtoIn, TariffDtoOut, Tariff> {
    private final TariffMapper tariffMapper;
    private final TariffRepository tariffRepository;
    private final ErrorHandleService errorHandleService;

    @Override
    public List<TariffDtoOut> getAll(Pageable pageable) {
        return tariffRepository.findAll(pageable)
                .stream()
                .map(tariffMapper::toDtoOut)
                .toList();
    }

    @Override
    public TariffDtoOut getOne(Long id) {
        return tariffMapper.toDtoOut(getOneEntity(id));
    }

    @Override
    public TariffDtoOut newItem(TariffDtoIn dtoIn) {
        Tariff tariff = tariffMapper.toEntity(dtoIn);
        return tariffMapper.toDtoOut(tariffRepository.save(tariff));
    }

    @Override
    public TariffDtoOut editItem(TariffDtoIn dtoIn, Long id) {
        if (!tariffRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, TARIFF_NOT_FOUND_MESSAGE);
        }
        Tariff tariff = tariffMapper.toEntity(dtoIn);
        tariff.setId(id);
        return tariffMapper.toDtoOut(tariffRepository.save(tariff));
    }

    @Override
    public void deleteItem(Long id) {
        if (!tariffRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, TARIFF_NOT_FOUND_MESSAGE);
        }
        tariffRepository.deleteById(id);
    }

    @Override
    public Tariff getOneEntity(Long id) {
        return tariffRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, TARIFF_NOT_FOUND_MESSAGE));
    }

}
