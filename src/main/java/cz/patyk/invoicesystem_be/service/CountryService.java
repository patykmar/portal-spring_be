package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.entities.Country;
import cz.patyk.invoicesystem_be.mapper.CountryMapper;
import cz.patyk.invoicesystem_be.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private static final String NOT_FOUND_MESSAGE = "Country not found";

    private final CountryMapper countryMapper;
    private final CountryRepository countryRepository;
    private final ErrorHandleService errorHandleService;

    public List<CountryDto> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(countryMapper::toDto)
                .toList();
    }

    public CountryDto getCountry(Long id) {
        return countryMapper.toDto(
                countryRepository.findById(id)
                        .orElseThrow(() -> errorHandleService.handleNotFoundError(id, NOT_FOUND_MESSAGE)));
    }

    public CountryDto newCountry(CountryDto countryDto) {
        Country country = countryMapper.toEntity(countryDto);
        return countryMapper.toDto(countryRepository.save(country));
    }

    public CountryDto edit(CountryDto countryDto, Long id) {
        if (!countryRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, NOT_FOUND_MESSAGE);
        }
        Country country = countryMapper.toEntity(countryDto);
        country.setId(id);
        return countryMapper.toDto(countryRepository.save(country));
    }

    public void delete(Long id) {
        if (!countryRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, NOT_FOUND_MESSAGE);
        }
        countryRepository.deleteById(id);
    }
}
