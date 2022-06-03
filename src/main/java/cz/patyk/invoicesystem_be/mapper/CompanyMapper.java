package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.entities.Address;
import cz.patyk.invoicesystem_be.service.AddressServices;
import org.mapstruct.Mapper;

import cz.patyk.invoicesystem_be.dto.in.CompanyDtoIn;
import cz.patyk.invoicesystem_be.dto.out.CompanyDtoOut;
import cz.patyk.invoicesystem_be.entities.Company;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, CountryMapper.class})
public abstract class CompanyMapper {
	@Autowired
	protected AddressServices addressServices;

	@Mapping(target = "address", source = "address.id")
	@Mapping(target = "addressDtoOut", source = "company.address")
	public abstract CompanyDtoOut toDto(Company company);

	@Mapping(target = "address", expression = "java(getAddress(companyDtoIn.getAddress()))")
	public abstract Company toEntity(CompanyDtoIn companyDtoIn);

	public Address getAddress(Long address){
		return addressServices.getEntityById(address);
	}
}
