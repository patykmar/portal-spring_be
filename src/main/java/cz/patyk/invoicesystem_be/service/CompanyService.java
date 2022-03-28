package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.in.CompanyDtoIn;
import cz.patyk.invoicesystem_be.dto.out.CompanyDtoOut;
import cz.patyk.invoicesystem_be.entities.Company;
import cz.patyk.invoicesystem_be.mapper.CompanyMapper;
import cz.patyk.invoicesystem_be.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private static final String NOT_FOUND_MESSAGE = "Company not found";

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final ErrorHandleService errorHandleService;

    public List<CompanyDtoOut> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toDto)
                .toList();
    }

    public CompanyDtoOut getCompany(Long id) {
        return companyMapper.toDto(
                companyRepository.findById(id)
                        .orElseThrow(() -> errorHandleService.handleNotFoundError(id, NOT_FOUND_MESSAGE))
        );
    }

    public CompanyDtoOut newCompany(CompanyDtoIn companyDtoIn) {
        Company company = companyMapper.toEntity(companyDtoIn);
        return companyMapper.toDto(companyRepository.save(company));
    }

    public CompanyDtoOut edit(CompanyDtoIn companyDtoIn, Long id) {
        if (!companyRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, NOT_FOUND_MESSAGE);
        }
        Company company = companyMapper.toEntity(companyDtoIn);
        company.setId(id);
        return companyMapper.toDto(companyRepository.save(company));
    }

    public void delete(Long id) {
        if (!companyRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, NOT_FOUND_MESSAGE);
        }
        companyRepository.deleteById(id);
    }
}
