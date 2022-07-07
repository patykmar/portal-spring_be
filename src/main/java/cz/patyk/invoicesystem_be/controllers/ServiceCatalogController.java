package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.in.ServiceCatalogDtoIn;
import cz.patyk.invoicesystem_be.dto.out.ServiceCatalogDtoOut;
import cz.patyk.invoicesystem_be.service.ServiceCatalogService;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service-catalog")
public class ServiceCatalogController {
    private final ServiceCatalogService serviceCatalogService;

    @GetMapping("")
    public ResponseEntity<CollectionModel<ServiceCatalogDtoOut>> getAll(
            @PageableDefault final Pageable pageable
    ) {
        List<ServiceCatalogDtoOut> serviceCatalogDtoOuts = serviceCatalogService.getAll(pageable);
        serviceCatalogDtoOuts.forEach(serviceCatalogDtoOut -> serviceCatalogDtoOut.add(
                WebMvcLinkBuilder.linkTo(ServiceCatalogController.class)
                        .slash(serviceCatalogDtoOut.getId())
                        .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(serviceCatalogDtoOuts).add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ServiceCatalogController.class).getAll(pageable)
                ).withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceCatalogDtoOut> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(serviceCatalogService.getOne(id));
    }

    @PostMapping("")
    public ResponseEntity<ServiceCatalogDtoOut> newItem(
            @Valid @RequestBody ServiceCatalogDtoIn serviceCatalogDtoIn
    ) {
        return ResponseEntity.ok(serviceCatalogService.newItem(serviceCatalogDtoIn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceCatalogDtoOut> editItem(
            @Valid @RequestBody ServiceCatalogDtoIn serviceCatalogDtoIn,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(serviceCatalogService.editItem(serviceCatalogDtoIn, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long id
    ) {
        serviceCatalogService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
