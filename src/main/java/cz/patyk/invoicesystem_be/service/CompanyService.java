package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.in.CompanyDtoIn;
import cz.patyk.invoicesystem_be.dto.out.CompanyDtoOut;
import cz.patyk.invoicesystem_be.entities.Company;
import cz.patyk.invoicesystem_be.mapper.CompanyMapper;
import cz.patyk.invoicesystem_be.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.patyk.invoicesystem_be.service.ServiceConstants.COMPANY_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class CompanyService implements CrudService<CompanyDtoIn, CompanyDtoOut, Company> {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final ErrorHandleService errorHandleService;

    public List<CompanyDtoOut> getAll(Pageable pageable) {
        return companyRepository.findAll(pageable)
                .stream()
                .map(companyMapper::toDto)
                .toList();
    }

    public CompanyDtoOut getOne(Long id) {
        return companyMapper.toDto(getOneEntity(id));
    }

    @Override
    public Company getOneEntity(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, COMPANY_NOT_FOUND_MESSAGE));
    }

    public CompanyDtoOut newItem(CompanyDtoIn companyDtoIn) {
        Company company = companyMapper.toEntity(companyDtoIn);
        return companyMapper.toDto(companyRepository.save(company));
    }

    public CompanyDtoOut editItem(CompanyDtoIn companyDtoIn, Long id) {
        if (!companyRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, COMPANY_NOT_FOUND_MESSAGE);
        }
        Company company = companyMapper.toEntity(companyDtoIn);
        company.setId(id);
        return companyMapper.toDto(companyRepository.save(company));
    }

    public void deleteItem(Long id) {
        if (!companyRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, COMPANY_NOT_FOUND_MESSAGE);
        }
        companyRepository.deleteById(id);
    }
}
