package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.AddressDto;
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

    public List<AddressDto> getAllAddresses(){
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toDto)
                .toList();
    }

    public AddressDto getAddress(Long id){
        return addressMapper
                .toDto(addressRepository.getById(id));
    }
}
