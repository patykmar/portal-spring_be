package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.out.AddressDtoOut;
import cz.patyk.invoicesystem_be.dto.in.AddressDtoIn;
import cz.patyk.invoicesystem_be.service.AddressServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/self/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressServices addressServices;

    @GetMapping("")
    public ResponseEntity<List<AddressDtoOut>> getAllAddresses(
            @PageableDefault() final Pageable pageable
    ) {
        log.info("Retrieved data from {} and {} method", AddressController.class, "getAllAddresses");
        return ResponseEntity.ok(addressServices.getAllAddressesData(pageable));
    }

    @GetMapping("/data")
    public ResponseEntity<CollectionModel<AddressDtoOut>> getAllAddressData(
            @PageableDefault() final Pageable pageable
    ) {
        log.info("Retrieved data from {} and {} method", AddressController.class, "getAllAddressData");
        CollectionModel<AddressDtoOut> addressDtoOutCollectionModel =
                CollectionModel.of(addressServices.getAllAddressesData(pageable));

        addressDtoOutCollectionModel.add(
                linkTo(
                        methodOn(AddressController.class)
                                .getAllAddressData(pageable)
                )
                        .withSelfRel());

        return ResponseEntity.ok(addressDtoOutCollectionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDtoOut> getAddress(@PathVariable Long id) {
        return ResponseEntity.ok(addressServices.getAddress(id));
    }

    @PostMapping("")
    public ResponseEntity<AddressDtoOut> newAddress(@RequestBody AddressDtoIn addressDtoIn) {
        return ResponseEntity.ok(addressServices.newAddress(addressDtoIn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDtoOut> edit(
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
