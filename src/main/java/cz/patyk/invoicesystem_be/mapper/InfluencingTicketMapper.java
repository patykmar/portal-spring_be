package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.InfluencingTicketDto;
import cz.patyk.invoicesystem_be.entities.InfluencingTicket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InfluencingTicketMapper {
    @Mapping(source = "forPriority", target = "isForPriority")
    @Mapping(source = "forImpact", target = "isForImpact")
    InfluencingTicketDto toDto(InfluencingTicket influencingTicket);

    @Mapping(source = "forPriority", target = "isForPriority")
    @Mapping(source = "forImpact", target = "isForImpact")
    InfluencingTicket toEntity(InfluencingTicketDto influencingTicketDto);
}
