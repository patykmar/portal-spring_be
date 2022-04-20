package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.in.CompanyDtoIn;
import cz.patyk.invoicesystem_be.dto.out.CompanyDtoOut;
import cz.patyk.invoicesystem_be.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("")
    public ResponseEntity<CollectionModel<CompanyDtoOut>> getAll(
            @PageableDefault() final Pageable pageable
    ) {
        List<CompanyDtoOut> companyDtoOuts = companyService.getAll(pageable);

        companyDtoOuts.forEach(companyDtoOut -> companyDtoOut.add(
                linkTo(CompanyController.class)
                        .slash(companyDtoOut.getId())
                        .withSelfRel()
        ));
        return ResponseEntity.ok(CollectionModel.of(companyDtoOuts).add(
                linkTo(methodOn(CompanyController.class).getAll(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDtoOut> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(companyService.getOne(id));
    }

    @PostMapping("")
    public ResponseEntity<CompanyDtoOut> newItem(
            @RequestBody CompanyDtoIn companyDtoIn
    ) {
        return ResponseEntity.ok(
                companyService.newItem(companyDtoIn)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDtoOut> editItem(
            @RequestBody CompanyDtoIn companyDtoIn,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                companyService.editItem(companyDtoIn, id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        companyService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}

