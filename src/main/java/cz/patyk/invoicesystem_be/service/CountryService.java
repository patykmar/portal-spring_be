package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.entities.Country;
import cz.patyk.invoicesystem_be.mapper.CountryMapper;
import cz.patyk.invoicesystem_be.repositories.CountryRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
    @NonNull
    private final CountryMapper countryMapper;
    @NonNull
    private final CountryRepository countryRepository;

    public CollectionModel<CountryDto> getAllCountries() {
        List<CountryDto> countryDtos = countryRepository.findAll()
                .stream()
                .map(countryMapper::toDto)
                .collect(Collectors.toList());
        return CollectionModel.of(countryDtos);
    }

    public Country toEntity(CountryDto countryDto) {
        return countryMapper.toEntity(countryDto);
    }

    public CountryDto toDto(Country country) {
        return countryMapper.toDto(country);
    }
}