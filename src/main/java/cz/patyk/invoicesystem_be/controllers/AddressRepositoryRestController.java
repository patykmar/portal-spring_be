package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.out.AddressDtoOut;
import cz.patyk.invoicesystem_be.service.AddressServices;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RepositoryRestController
public class AddressRepositoryRestController {
    @NonNull
    private final AddressServices addressServices;

    @GetMapping(value = "/addresses")
    @ResponseBody
    public ResponseEntity<CollectionModel<AddressDtoOut>> getAddresses(
            @PageableDefault() final Pageable pageable
    ) {
        log.info("Retrieved data from {} and {} method", AddressRepositoryRestController.class, "getAddresses");

        CollectionModel<AddressDtoOut> addressDtoOutCollectionModel =
                CollectionModel.of(addressServices.getAllAddressesData(pageable));

        addressDtoOutCollectionModel.add(linkTo(
                methodOn(AddressRepositoryRestController.class)
                        .getAddresses(pageable)
        )
                .withSelfRel());

        return ResponseEntity.ok(addressDtoOutCollectionModel);
    }
}
