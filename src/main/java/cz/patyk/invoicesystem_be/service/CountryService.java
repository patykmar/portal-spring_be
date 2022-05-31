package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.entities.Country;
import cz.patyk.invoicesystem_be.mapper.CountryMapper;
import cz.patyk.invoicesystem_be.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.patyk.invoicesystem_be.service.ServiceConstants.COUNTRY_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryMapper countryMapper;
    private final CountryRepository countryRepository;
    private final ErrorHandleService errorHandleService;

    public List<CountryDto> getAllCountries(Pageable pageable) {
        return countryRepository.findAll(pageable)
                .stream()
                .map(countryMapper::toDto)
                .toList();
    }

    public CountryDto getDtoById(Long id) {
        return countryMapper.toDto(getEntityById(id));
    }

    public CountryDto newCountry(CountryDto countryDto) {
        Country country = countryMapper.toEntity(countryDto);
        return countryMapper.toDto(countryRepository.save(country));
    }

    public CountryDto edit(CountryDto countryDto, Long id) {
        if (!countryRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, COUNTRY_NOT_FOUND_MESSAGE);
        }
        Country country = countryMapper.toEntity(countryDto);
        country.setId(id);
        return countryMapper.toDto(countryRepository.save(country));
    }

    public void delete(Long id) {
        if (!countryRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, COUNTRY_NOT_FOUND_MESSAGE);
        }
        countryRepository.deleteById(id);
    }

    public Country getEntityById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, COUNTRY_NOT_FOUND_MESSAGE));
    }

}
