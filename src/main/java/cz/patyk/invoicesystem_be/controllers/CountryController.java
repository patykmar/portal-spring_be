package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping("")
    public ResponseEntity<CollectionModel<CountryDto>> getAll(
            @PageableDefault() final Pageable pageable
    ) {
        List<CountryDto> countryDtos = countryService.getAllCountries(pageable);
        countryDtos.forEach(countryDto -> countryDto.add(
                WebMvcLinkBuilder.linkTo(CountryController.class)
                        .slash(countryDto.getId())
                        .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(countryDtos).add(
                linkTo(methodOn(CountryController.class).getAll(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(countryService.getDtoById(id));
    }

    @PostMapping("")
    public ResponseEntity<CountryDto> newItem(
            @RequestBody CountryDto countryDto
    ) {
        return ResponseEntity.ok(countryService.newCountry(countryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDto> editItem(
            @RequestBody CountryDto countryDto,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(countryService.edit(countryDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
