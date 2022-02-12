package cz.patyk.invoicesystem_be.mapper;

import org.mapstruct.Mapper;

import cz.patyk.invoicesystem_be.dto.in.CompanyDtoIn;
import cz.patyk.invoicesystem_be.dto.out.CompanyDtoOut;
import cz.patyk.invoicesystem_be.entities.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	Company toEntity(CompanyDtoIn companyDtoIn);
	
	CompanyDtoOut toDto(Company company);
}
