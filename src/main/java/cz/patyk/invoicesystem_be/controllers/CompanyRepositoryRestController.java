package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.entities.Company;
import cz.patyk.invoicesystem_be.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
@AllArgsConstructor
public class CompanyRepositoryRestController {
    private final CompanyRepository companyRepository;

    @GetMapping(value = "/companies")
    public @ResponseBody ResponseEntity<CollectionModel<Company>> getCompanies() {
        List<Company> companies = companyRepository.findAll();

        CollectionModel<Company> companiesCollections = CollectionModel.of(companies);

        companiesCollections.add(linkTo(methodOn(CompanyRepositoryRestController.class).getCompanies()).withSelfRel());

        return ResponseEntity.ok(companiesCollections);
    }
}
