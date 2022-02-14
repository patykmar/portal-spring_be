package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.ServiceCatalogDto;
import cz.patyk.invoicesystem_be.entities.ServiceCatalog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceCatalogMapper {
    ServiceCatalog toEntity(ServiceCatalogDto serviceCatalogDto);

    ServiceCatalogDto toDto(ServiceCatalog serviceCatalog);
}
