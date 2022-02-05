package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.out.CompanyDtoOut;
import cz.patyk.invoicesystem_be.mapper.CompanyMapper;
import cz.patyk.invoicesystem_be.repositories.CompanyRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    @NonNull
    private final CompanyRepository companyRepository;
    @NonNull
    private final CompanyMapper companyMapper;

    public List<CompanyDtoOut> getAllCompanies(){
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toDto)
                .collect(Collectors.toList());
    }
}
