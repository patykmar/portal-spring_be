package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.GeneralStateDto;
import cz.patyk.invoicesystem_be.entities.GeneralState;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralStateMapper {
    GeneralState toEntity(GeneralStateDto generalStateDto);

    GeneralStateDto toDto(GeneralState generalState);
}
