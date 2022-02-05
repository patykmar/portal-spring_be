package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.VatDto;
import cz.patyk.invoicesystem_be.mapper.VatMapper;
import cz.patyk.invoicesystem_be.repositories.VatRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VatService {
    @NonNull
    private final VatRepository vatRepository;
    @NonNull
    private final VatMapper vatMapper;

    public List<VatDto> getAllVats(){
        return vatRepository.findAll()
                .stream()
                .map(vatMapper::toDto)
                .collect(Collectors.toList());
    }

}
