package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.out.CompanyDtoOut;
import cz.patyk.invoicesystem_be.service.CompanyService;
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
public class CompanyRepositoryRestController {
    @NonNull
    private final CompanyService companyService;

    @GetMapping(value = "/companies")
    public @ResponseBody
    ResponseEntity<CollectionModel<CompanyDtoOut>> getCompanies() {
        CollectionModel<CompanyDtoOut> companiesCollections = CollectionModel.of(companyService.getAllCompanies());

        companiesCollections.add(
                linkTo(methodOn(CompanyRepositoryRestController.class).getCompanies())
                        .withSelfRel());

        return ResponseEntity.ok(companiesCollections);
    }
}
