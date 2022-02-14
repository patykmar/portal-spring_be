package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.SlaDto;
import cz.patyk.invoicesystem_be.entities.Sla;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SlaMapper {
    Sla toEntity(SlaDto slaDto);

    SlaDto toDto(Sla sla);
}
