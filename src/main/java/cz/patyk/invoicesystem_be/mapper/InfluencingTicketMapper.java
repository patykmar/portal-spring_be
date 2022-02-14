package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.InfluencingTicketDto;
import cz.patyk.invoicesystem_be.entities.InfluencingTicket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InfluencingTicketMapper {
    InfluencingTicketDto toDto(InfluencingTicket influencingTicket);

    InfluencingTicket toEntity(InfluencingTicketDto influencingTicketDto);
}
