package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.SlaDtoIn;
import cz.patyk.invoicesystem_be.dto.out.SlaDtoOut;
import cz.patyk.invoicesystem_be.entities.InfluencingTicket;
import cz.patyk.invoicesystem_be.entities.Sla;
import cz.patyk.invoicesystem_be.entities.Tariff;
import cz.patyk.invoicesystem_be.entities.TicketType;
import cz.patyk.invoicesystem_be.service.InfluencingTicketService;
import cz.patyk.invoicesystem_be.service.TariffService;
import cz.patyk.invoicesystem_be.service.TicketTypeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(uses = {TicketTypeMapper.class, TariffMapper.class, InfluencingTicketMapper.class})
public abstract class SlaMapper {
    @Autowired
    protected TariffService tariffService;
    @Autowired
    protected InfluencingTicketService influencingTicketService;
    @Autowired
    protected TicketTypeService ticketTypeService;

    @Mapping(target = "tariff", expression = "java(getTariff(slaDto.getTariffId()))")
    @Mapping(target = "priority", expression = "java(getPriority(slaDto.getPriorityId()))")
    @Mapping(target = "ticketType", expression = "java(getTicketTypeId(slaDto.getTicketTypeId()))")
    public abstract Sla toEntity(SlaDtoIn slaDto);

    @Mapping(source = "tariff.id", target = "tariffId")
    @Mapping(source = "priority.id", target = "priorityId")
    @Mapping(source = "ticketType.id", target = "ticketTypeId")
    public abstract SlaDtoOut toDtoOut(Sla sla);

    protected Tariff getTariff(Long id) {
        return tariffService.getOneEntity(id);
    }

    protected InfluencingTicket getPriority(Long id) {
        return influencingTicketService.getOneEntity(id);
    }

    protected TicketType getTicketTypeId(Long id) {
        return ticketTypeService.getOneEntity(id);
    }
}
