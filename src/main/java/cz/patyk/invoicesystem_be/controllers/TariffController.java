package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.in.TariffDtoIn;
import cz.patyk.invoicesystem_be.dto.out.TariffDtoOut;
import cz.patyk.invoicesystem_be.service.TariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
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
@RequiredArgsConstructor
@RequestMapping("/api/tariffs")
public class TariffController {
    private final TariffService tariffService;

    @GetMapping("")
    public ResponseEntity<CollectionModel<TariffDtoOut>> getAll(
            @PageableDefault() final Pageable pageable
    ) {
        List<TariffDtoOut> tariffDtoOuts = tariffService.getAll(pageable);

        tariffDtoOuts.forEach(tariffDtoOut -> tariffDtoOut.add(
                linkTo(TariffController.class)
                        .slash(tariffDtoOut.getId())
                        .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(tariffDtoOuts).add(
                linkTo(methodOn(TariffController.class).getAll(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TariffDtoOut> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(tariffService.getOne(id));
    }

    @PostMapping("")
    public ResponseEntity<TariffDtoOut> newItem(
            @RequestBody TariffDtoIn tariffDtoIn
    ) {
        return ResponseEntity.ok(tariffService.newItem(tariffDtoIn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TariffDtoOut> editItem(
            @RequestBody TariffDtoIn tariffDtoIn,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(tariffService.editItem(tariffDtoIn, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        tariffService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
