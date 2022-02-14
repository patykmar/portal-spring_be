package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.TicketTypeDto;
import cz.patyk.invoicesystem_be.entities.TicketType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketTypeMapper {
    TicketType toEntity(TicketTypeDto ticketTypeDto);

    TicketTypeDto toDto(TicketType ticketType);
}
