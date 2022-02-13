package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.AddressDto;
import cz.patyk.invoicesystem_be.service.AddressServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressServices addressServices;

    @GetMapping("")
    public List<AddressDto> getAllAddresses() {
        return addressServices.getAllAddresses();
    }
}
