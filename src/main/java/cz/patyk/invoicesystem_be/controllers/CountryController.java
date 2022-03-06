package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping("")
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.getCountry(id));
    }

    @PostMapping("")
    public ResponseEntity<CountryDto> newCountry(@RequestBody CountryDto countryDto) {
        return ResponseEntity.ok(countryService.newCountry(countryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDto> edit(
            @RequestBody CountryDto countryDto,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(countryService.edit(countryDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
