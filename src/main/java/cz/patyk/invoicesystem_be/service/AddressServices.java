package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.out.AddressDtoOut;
import cz.patyk.invoicesystem_be.dto.in.AddressDtoIn;
import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.exceptions.ApplicationException;
import cz.patyk.invoicesystem_be.mapper.AddressMapper;
import cz.patyk.invoicesystem_be.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServices {
    private static final String NOT_FOUND_MESSAGE = "Address not found";

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public List<AddressDtoOut> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toDto)
                .toList();
    }

    public AddressDtoOut getAddress(Long id) {
        return addressMapper.toDto(
                addressRepository.findById(id)
                        .orElseThrow(() -> new ApplicationException(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND))
        );
    }

    public AddressDtoOut newAddress(AddressDtoIn addressDtoIn) {
        Address address = addressMapper.toEntity(addressDtoIn);
        return addressMapper.toDto(addressRepository.save(address));
    }

    public AddressDtoOut edit(AddressDtoIn addressDtoIn, Long id) {
        if (!addressRepository.existsById(id)) {
            throw new ApplicationException(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
        }
        Address address = addressMapper.toEntity(addressDtoIn);
        address.setId(id);
        return addressMapper.toDto(addressRepository.save(address));
    }

    //TODO: FIXIT
    //When tried delete exist item then I received SQL exception
    //org.springframework.dao.DataIntegrityViolationException: could not execute statement; SQL [n/a]; constraint [\"FKGFIFM4874CE6MECWJ54WDB3MA: PUBLIC.COMPANY FOREIGN KEY(ADDRESS_ID) REFERENCES PUBLIC.ADDRESS(ID) (12)\"
    public void delete(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new ApplicationException(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
        }
        addressRepository.deleteById(id);
    }
}
