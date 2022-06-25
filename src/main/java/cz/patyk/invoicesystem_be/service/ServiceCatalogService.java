package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.in.ServiceCatalogDtoIn;
import cz.patyk.invoicesystem_be.dto.out.ServiceCatalogDtoOut;
import cz.patyk.invoicesystem_be.entities.ServiceCatalog;
import cz.patyk.invoicesystem_be.mapper.ServiceCatalogMapper;
import cz.patyk.invoicesystem_be.repositories.ServiceCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceCatalogService {
    private final ServiceCatalogRepository serviceCatalogRepository;
    private final ServiceCatalogMapper serviceCatalogMapper;
    private final ErrorHandleService errorHandleService;

    public List<ServiceCatalogDtoOut> getAll(Pageable pageable) {
        return serviceCatalogRepository.findAll(pageable)
                .stream()
                .map(serviceCatalogMapper::toDtoOut)
                .toList();
    }

    public ServiceCatalogDtoOut getOne(Long id) {
        return serviceCatalogMapper.toDtoOut(serviceCatalogRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, ServiceConstants.SERVICE_CATALOG_NOT_FOUND_MESSAGE)));
    }

    public ServiceCatalogDtoOut newItem(ServiceCatalogDtoIn serviceCatalogDtoIn) {
        ServiceCatalog serviceCatalog = serviceCatalogMapper.toEntity(serviceCatalogDtoIn);
        return serviceCatalogMapper.toDtoOut(serviceCatalog);
    }

    public ServiceCatalogDtoOut editItem(ServiceCatalogDtoIn serviceCatalogDtoIn, Long id) {
        if (!serviceCatalogRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, ServiceConstants.SERVICE_CATALOG_NOT_FOUND_MESSAGE);
        }
        ServiceCatalog serviceCatalog = serviceCatalogMapper.toEntity(serviceCatalogDtoIn);
        serviceCatalog.setId(id);
        return serviceCatalogMapper.toDtoOut(serviceCatalogRepository.save(serviceCatalog));
    }

    public void deleteItem(Long id) {
        if (!serviceCatalogRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, ServiceConstants.SERVICE_CATALOG_NOT_FOUND_MESSAGE);
        }
        serviceCatalogRepository.deleteById(id);
    }

}
