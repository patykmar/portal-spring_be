package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.TicketDto;
import cz.patyk.invoicesystem_be.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TicketMapper {
    @Mapping(target = "workInventory", ignore = true)
    @Mapping(target = "userResolved", ignore = true)
    @Mapping(target = "userCreated", ignore = true)
    @Mapping(target = "ticketType", ignore = true)
    @Mapping(target = "ticketState", ignore = true)
    @Mapping(target = "ticketCloseState", ignore = true)
    @Mapping(target = "queueUser", ignore = true)
    @Mapping(target = "priority", ignore = true)
    @Mapping(target = "parentTicket", ignore = true)
    @Mapping(target = "impact", ignore = true)
    @Mapping(target = "ci", ignore = true)
    Ticket toEntity(TicketDto ticketDto);

    @Mapping(target = "workInventoryId", ignore = true)
    @Mapping(target = "userResolvedId", ignore = true)
    @Mapping(target = "userCreatedId", ignore = true)
    @Mapping(target = "ticketTypeId", ignore = true)
    @Mapping(target = "ticketStateId", ignore = true)
    @Mapping(target = "ticketCloseStateId", ignore = true)
    @Mapping(target = "queueUserId", ignore = true)
    @Mapping(target = "priorityId", ignore = true)
    @Mapping(target = "parentTicketId", ignore = true)
    @Mapping(target = "impactId", ignore = true)
    @Mapping(target = "ciId", ignore = true)
    TicketDto toDto(Ticket ticket);
}
