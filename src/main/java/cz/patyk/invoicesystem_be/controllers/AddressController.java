package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.out.AddressDtoOut;
import cz.patyk.invoicesystem_be.dto.in.AddressDtoIn;
import cz.patyk.invoicesystem_be.service.AddressServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressServices addressServices;

    @GetMapping("")
    public List<AddressDtoOut> getAllAddresses() {
        return addressServices.getAllAddresses();
    }

    @GetMapping("/{id}")
    public AddressDtoOut getAddress(@PathVariable Long id) {
        return addressServices.getAddress(id);
    }

    @PostMapping("")
    public AddressDtoOut newAddress(@RequestBody AddressDtoIn addressDtoIn) {
        return addressServices.newAddress(addressDtoIn);
    }

    @PutMapping("/{id}")
    public AddressDtoOut edit(
            @RequestBody AddressDtoIn addressDtoIn,
            @PathVariable Long id
    ) {
        return addressServices.edit(addressDtoIn, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        addressServices.delete(id);
    }
}
