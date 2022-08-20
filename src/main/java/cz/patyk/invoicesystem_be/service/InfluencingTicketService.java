package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.InfluencingTicketDto;
import cz.patyk.invoicesystem_be.entities.InfluencingTicket;
import cz.patyk.invoicesystem_be.mapper.InfluencingTicketMapper;
import cz.patyk.invoicesystem_be.repositories.InfluencingTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.patyk.invoicesystem_be.service.ServiceConstants.INFLUENCING_TICKET_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class InfluencingTicketService implements CommonDtoService<InfluencingTicketDto, InfluencingTicketDto>, CommonEntityService<InfluencingTicket> {
    private final InfluencingTicketRepository influencingTicketRepository;
    private final InfluencingTicketMapper influencingTicketMapper;
    private final ErrorHandleService errorHandleService;

    @Override
    public List<InfluencingTicketDto> getAll(Pageable pageable) {
        return influencingTicketRepository.findAll(pageable)
                .stream()
                .map(influencingTicketMapper::toDto)
                .toList();
    }

    public List<InfluencingTicketDto> getAllForPriority(Pageable pageable) {
        return influencingTicketRepository.findAllByIsForPriorityIsTrue(pageable)
                .stream()
                .map(influencingTicketMapper::toDto)
                .toList();
    }

    public List<InfluencingTicketDto> getAllForImpact(Pageable pageable) {
        return influencingTicketRepository.findAllByIsForImpactIsTrue(pageable)
                .stream()
                .map(influencingTicketMapper::toDto)
                .toList();
    }

    @Override
    public InfluencingTicketDto getOne(Long id) {
        return influencingTicketMapper.toDto(getOneEntity(id));
    }

    @Override
    public InfluencingTicketDto newItem(InfluencingTicketDto dtoIn) {
        InfluencingTicket influencingTicket = influencingTicketMapper.toEntity(dtoIn);
        return influencingTicketMapper.toDto(influencingTicketRepository.save(influencingTicket));
    }

    @Override
    public InfluencingTicketDto editItem(InfluencingTicketDto influencingTicketDto, Long id) {
        if (!influencingTicketRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, INFLUENCING_TICKET_NOT_FOUND_MESSAGE);
        }
        InfluencingTicket influencingTicket = influencingTicketMapper.toEntity(influencingTicketDto);
        influencingTicket.setId(id);
        return influencingTicketMapper.toDto(influencingTicketRepository.save(influencingTicket));
    }

    @Override
    public void deleteItem(Long id) {
        if (!influencingTicketRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, INFLUENCING_TICKET_NOT_FOUND_MESSAGE);
        }
        influencingTicketRepository.deleteById(id);
    }

    @Override
    public InfluencingTicket getOneEntity(Long id) {
        return influencingTicketRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, INFLUENCING_TICKET_NOT_FOUND_MESSAGE));
    }
}
