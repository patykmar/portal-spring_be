package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.GeneralStateDto;
import cz.patyk.invoicesystem_be.entities.GeneralState;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GeneralStateMapper {
    @Mapping(source = "forTicket", target = "isForTicket")
    @Mapping(source = "forCi", target = "isForCi")
    @Mapping(source = "forCloseState", target = "isForCloseState")
    GeneralState toEntity(GeneralStateDto generalStateDto);

    @Mapping(source = "forTicket", target = "isForTicket")
    @Mapping(source = "forCi", target = "isForCi")
    @Mapping(source = "forCloseState", target = "isForCloseState")
    GeneralStateDto toDto(GeneralState generalState);
}
