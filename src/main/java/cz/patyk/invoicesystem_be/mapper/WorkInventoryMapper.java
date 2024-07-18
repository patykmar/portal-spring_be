package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.WorkInventoryDto;
import cz.patyk.invoicesystem_be.entities.WorkInventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface WorkInventoryMapper {
    @Mapping(target = "tariff", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    WorkInventory toEntity(WorkInventoryDto workInventoryDto);


    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "tariffId", ignore = true)
    @Mapping(target = "invoiceId", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    WorkInventoryDto toDto(WorkInventory workInventory);
}
