package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.in.VatDtoIn;
import cz.patyk.invoicesystem_be.dto.out.VatDtoOut;
import cz.patyk.invoicesystem_be.entities.Vat;
import cz.patyk.invoicesystem_be.mapper.VatMapper;
import cz.patyk.invoicesystem_be.repositories.VatRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.patyk.invoicesystem_be.service.ServiceConstants.VAT_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class VatService {
    @NonNull
    private final VatRepository vatRepository;
    @NonNull
    private final VatMapper vatMapper;

    @NonNull
    private final ErrorHandleService errorHandleService;

    public List<VatDtoOut> getAllVats(Pageable pageable) {
        return vatRepository.findAll(pageable)
                .stream()
                .map(vatMapper::toDto)
                .toList();
    }

    public VatDtoOut getVat(Long id) {
        return vatMapper.toDto(getEntityById(id));
    }

    public Vat getEntityById(Long id) {
        return vatRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, VAT_NOT_FOUND_MESSAGE));
    }

    public VatDtoOut newVat(VatDtoIn vatDtoIn) {
        Vat vat = vatMapper.toEntity(vatDtoIn);
        return vatMapper.toDto(vatRepository.save(vat));
    }

    public VatDtoOut edit(VatDtoIn vatDtoIn, Long id) {
        if (!vatRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, VAT_NOT_FOUND_MESSAGE);
        }
        Vat vat = vatMapper.toEntity(vatDtoIn);
        vat.setId(id);
        return vatMapper.toDto(vatRepository.save(vat));
    }

    public void delete(Long id) {
        if (!vatRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, VAT_NOT_FOUND_MESSAGE);
        }
        vatRepository.deleteById(id);
    }

}
