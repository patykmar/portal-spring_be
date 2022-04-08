package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.service.CountryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
@RequiredArgsConstructor
public class CountryRepositoryRestController {
    @NonNull
    private final CountryService countryService;

    @GetMapping(value = "/countries")
    public @ResponseBody
    ResponseEntity<CollectionModel<CountryDto>> getCountries() {
        CollectionModel<CountryDto> countryDtoCollectionModel = CollectionModel.of(countryService.getAllCountries());

        countryDtoCollectionModel.add(
                linkTo(
                        methodOn(CompanyRepositoryRestController.class)
                                .getCompanies())
                        .withSelfRel()
        );
        return ResponseEntity.ok(countryDtoCollectionModel);
    }
}
