package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.TicketTypeDto;
import cz.patyk.invoicesystem_be.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketTypeMapper {
    @Mapping(source = "disable", target = "isDisable")
    TicketType toEntity(TicketTypeDto ticketTypeDto);

    @Mapping(source = "disable", target = "isDisable")
    TicketTypeDto toDto(TicketType ticketType);
}
