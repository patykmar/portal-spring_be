package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.GeneralLogDto;
import cz.patyk.invoicesystem_be.entities.GeneralLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralLogMapper {
    GeneralLogDto toDto(GeneralLog generalLog);

    GeneralLog toEntity(GeneralLogDto generalLogDto);
}
