package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.SlaDtoIn;
import cz.patyk.invoicesystem_be.dto.out.SlaDtoOut;
import cz.patyk.invoicesystem_be.entities.Sla;
import cz.patyk.invoicesystem_be.entities.Tariff;
import cz.patyk.invoicesystem_be.service.SlaService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

//@Mapper(componentModel = "spring", uses = {TicketTypeMapper.class, TariffMapper.class, InfluencingTicketMapper.class})
@Mapper(componentModel = "spring", uses = {TicketTypeMapper.class})
public abstract class SlaMapper {
    @Autowired
    protected SlaService slaService;

    @Mapping(source = "tariffId", target = "tariff.id")
    @Mapping(source = "priorityId", target = "priority.id")
    @Mapping(source = "ticketTypeId", target = "ticketType.id")
    public abstract Sla toEntity(SlaDtoIn slaDto);

    @Mapping(source = "tariff.id", target = "tariffId")
    @Mapping(source = "priority.id", target = "priorityId")
    @Mapping(source = "ticketType.id", target = "ticketTypeId")
    public abstract SlaDtoOut toDtoOut(Sla sla);

    public Sla getSla(Long sla) {
        return slaService.getEntityById(sla);
    }
}
