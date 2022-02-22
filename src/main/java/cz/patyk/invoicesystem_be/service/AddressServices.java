package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.AddressDto;
import cz.patyk.invoicesystem_be.dto.in.AddressDtoIn;
import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.mapper.AddressMapper;
import cz.patyk.invoicesystem_be.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServices {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public List<AddressDto> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toDto)
                .toList();
    }

    public AddressDto getAddress(Long id) {
        return addressMapper
                .toDto(addressRepository.getById(id));
    }

    public AddressDto newAddress(AddressDtoIn addressDtoIn) {
        Address address = addressMapper.toEntity(addressDtoIn);
        return addressMapper.toDto(addressRepository.save(address));
    }

    public AddressDto edit(AddressDtoIn addressDtoIn, Long id) {
        Address address = addressMapper.toEntity(addressDtoIn);
        address.setId(id);
        return addressMapper.toDto(addressRepository.save(address));
    }

    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
