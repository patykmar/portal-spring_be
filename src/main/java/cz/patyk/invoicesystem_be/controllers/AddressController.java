package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.out.AddressDtoOut;
import cz.patyk.invoicesystem_be.dto.in.AddressDtoIn;
import cz.patyk.invoicesystem_be.service.AddressServices;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressServices addressServices;

    @GetMapping(value = "", produces = {"application/hal+json"})
    public ResponseEntity<CollectionModel<AddressDtoOut>> getAll(
            @PageableDefault() final Pageable pageable
    ) {
        List<AddressDtoOut> addressDtoOuts = addressServices.getAllAddresses(pageable);

        addressDtoOuts.forEach(addressDtoOut -> addressDtoOut.add(
                linkTo(AddressController.class)
                        .slash(addressDtoOut.getId())
                        .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(addressDtoOuts).add(
                linkTo(methodOn(AddressController.class).getAll(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDtoOut> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(addressServices.getAddress(id));
    }

    @PostMapping("")
    public ResponseEntity<AddressDtoOut> newItem(@RequestBody AddressDtoIn addressDtoIn) {
        return ResponseEntity.ok(addressServices.newAddress(addressDtoIn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDtoOut> editItem(
            @RequestBody AddressDtoIn addressDtoIn,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(addressServices.edit(addressDtoIn, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
