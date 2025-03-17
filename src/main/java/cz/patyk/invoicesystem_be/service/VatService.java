package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.in.VatDtoIn;
import cz.patyk.invoicesystem_be.dto.out.VatDtoOut;
import cz.patyk.invoicesystem_be.entities.Vat;
import cz.patyk.invoicesystem_be.mapper.VatMapper;
import cz.patyk.invoicesystem_be.repositories.VatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.patyk.invoicesystem_be.service.ServiceConstants.VAT_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class VatService implements CrudService<VatDtoIn, VatDtoOut, Vat> {
    private final VatRepository vatRepository;
    private final VatMapper vatMapper;
    private final ErrorHandleService errorHandleService;

    public List<VatDtoOut> getAll(Pageable pageable) {
        return vatRepository.findAll(pageable)
                .stream()
                .map(vatMapper::toDto)
                .toList();
    }

    @Override
    public VatDtoOut getOne(Long id) {
        return vatMapper.toDto(getOneEntity(id));
    }

    @Override
    public Vat getOneEntity(Long id) {
        return vatRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, VAT_NOT_FOUND_MESSAGE));
    }

    public VatDtoOut newItem(VatDtoIn vatDtoIn) {
        Vat vat = vatMapper.toEntity(vatDtoIn);
        return vatMapper.toDto(vatRepository.save(vat));
    }

    @Override
    public VatDtoOut editItem(VatDtoIn vatDtoIn, Long id) {
        if (!vatRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, VAT_NOT_FOUND_MESSAGE);
        }
        Vat vat = vatMapper.toEntity(vatDtoIn);
        vat.setId(id);
        return vatMapper.toDto(vatRepository.save(vat));
    }

    @Override
    public void deleteItem(Long id) {
        if (!vatRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, VAT_NOT_FOUND_MESSAGE);
        }
        vatRepository.deleteById(id);
    }

}
