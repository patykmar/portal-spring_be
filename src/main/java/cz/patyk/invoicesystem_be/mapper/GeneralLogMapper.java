package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.GeneralLogDto;
import cz.patyk.invoicesystem_be.entities.GeneralLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GeneralLogMapper {
    @Mapping(target = "ciId", ignore = true)
    @Mapping(target = "ticketId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    GeneralLogDto toDto(GeneralLog generalLog);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "ticket", ignore = true)
    @Mapping(target = "ci", ignore = true)
    GeneralLog toEntity(GeneralLogDto generalLogDto);
}
