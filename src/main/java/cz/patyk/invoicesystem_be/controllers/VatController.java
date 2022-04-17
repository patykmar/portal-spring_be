package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.in.VatDtoIn;
import cz.patyk.invoicesystem_be.dto.out.VatDtoOut;
import cz.patyk.invoicesystem_be.service.VatService;
import lombok.NonNull;
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
@RequestMapping("/vats")
@RequiredArgsConstructor
public class VatController {
    @NonNull
    private final VatService vatService;

    @GetMapping(value = "", produces = {"application/hal+json"})
    public ResponseEntity<CollectionModel<VatDtoOut>> getAll(
            @PageableDefault() final Pageable pageable
    ) {
        List<VatDtoOut> vatDtoList = vatService.getAllVats(pageable);

        vatDtoList.forEach(vatDto -> vatDto.add(
                linkTo(VatController.class)
                        .slash(vatDto.getId())
                        .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(vatDtoList).add(
                linkTo(methodOn(VatController.class).getAll(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VatDtoOut> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(vatService.getVat(id));
    }

    @PostMapping("")
    public ResponseEntity<VatDtoOut> newItem(
            @RequestBody VatDtoIn vatDtoIn
    ) {
        return ResponseEntity.ok(vatService.newVat(vatDtoIn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VatDtoOut> editItem(
            @RequestBody VatDtoIn vatDtoIn,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(vatService.edit(vatDtoIn, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long id
    ) {
        vatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
