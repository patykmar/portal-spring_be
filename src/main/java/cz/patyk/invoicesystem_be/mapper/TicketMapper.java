package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.TicketDto;
import cz.patyk.invoicesystem_be.entities.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Ticket toEntity(TicketDto ticketDto);

    TicketDto toDto(Ticket ticket);
}
