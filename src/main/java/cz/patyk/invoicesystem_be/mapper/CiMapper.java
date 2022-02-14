package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.CiDto;
import cz.patyk.invoicesystem_be.entities.Ci;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CiMapper {
    CiDto toDto(Ci ci);

    Ci toEntity(CiDto ciDto);
}
