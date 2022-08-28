package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.TicketTypeDto;
import cz.patyk.invoicesystem_be.entities.TicketType;
import cz.patyk.invoicesystem_be.mapper.TicketTypeMapper;
import cz.patyk.invoicesystem_be.repositories.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.patyk.invoicesystem_be.service.ServiceConstants.COMPANY_NOT_FOUND_MESSAGE;
import static cz.patyk.invoicesystem_be.service.ServiceConstants.TICKET_TYPE_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class TicketTypeService implements CrudService<TicketTypeDto, TicketTypeDto, TicketType> {
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketTypeMapper ticketTypeMapper;
    private final ErrorHandleService errorHandleService;

    @Override
    public List<TicketTypeDto> getAll(Pageable pageable) {
        return ticketTypeRepository.findAll(pageable)
                .stream()
                .map(ticketTypeMapper::toDto)
                .toList();
    }

    @Override
    public TicketTypeDto getOne(Long id) {
        return ticketTypeMapper.toDto(getOneEntity(id));
    }

    @Override
    public TicketTypeDto newItem(TicketTypeDto ticketTypeDto) {
        TicketType ticketType = ticketTypeMapper.toEntity(ticketTypeDto);
        return ticketTypeMapper.toDto(ticketTypeRepository.save(ticketType));
    }

    @Override
    public TicketTypeDto editItem(TicketTypeDto ticketTypeDto, Long id) {
        if (!ticketTypeRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, TICKET_TYPE_NOT_FOUND_MESSAGE);
        }
        TicketType ticketType = ticketTypeMapper.toEntity(ticketTypeDto);
        ticketType.setId(id);
        return ticketTypeMapper.toDto(ticketTypeRepository.save(ticketType));
    }

    @Override
    public void deleteItem(Long id) {
        if (!ticketTypeRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, COMPANY_NOT_FOUND_MESSAGE);
        }
        ticketTypeRepository.deleteById(id);
    }

    @Override
    public TicketType getOneEntity(Long id) {
        return ticketTypeRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, TICKET_TYPE_NOT_FOUND_MESSAGE));
    }
}
