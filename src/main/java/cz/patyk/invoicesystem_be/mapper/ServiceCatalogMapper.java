package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.ServiceCatalogDtoIn;
import cz.patyk.invoicesystem_be.dto.out.ServiceCatalogDtoOut;
import cz.patyk.invoicesystem_be.entities.ServiceCatalog;
import cz.patyk.invoicesystem_be.entities.Vat;
import cz.patyk.invoicesystem_be.service.VatService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {VatMapper.class})
public abstract class ServiceCatalogMapper {
    @Autowired
    protected VatService vatService;

    @Mapping(target = "vat", expression = "java(getVat(serviceCatalogDtoIn.getVat()))")
    public abstract ServiceCatalog toEntity(ServiceCatalogDtoIn serviceCatalogDtoIn);

    @Mapping(target = "vat", source = "vat.id")
    public abstract ServiceCatalogDtoOut toDtoOut(ServiceCatalog serviceCatalog);

    public Vat getVat(Long vat){
        return vatService.getEntityById(vat);
    }
}
