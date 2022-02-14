package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.WorkInventoryDto;
import cz.patyk.invoicesystem_be.entities.WorkInventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkInventoryMapper {
    WorkInventory toEntity(WorkInventoryDto workInventoryDto);

    WorkInventoryDto toDto(WorkInventory workInventory);
}
