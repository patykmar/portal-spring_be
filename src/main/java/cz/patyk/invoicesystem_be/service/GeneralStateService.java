package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.GeneralStateDto;
import cz.patyk.invoicesystem_be.entities.GeneralState;
import cz.patyk.invoicesystem_be.mapper.GeneralStateMapper;
import cz.patyk.invoicesystem_be.repositories.GeneralStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.patyk.invoicesystem_be.service.ServiceConstants.GENERAL_STATE_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class GeneralStateService {
    private final GeneralStateMapper generalStateMapper;
    private final GeneralStateRepository generalStateRepository;
    private final ErrorHandleService errorHandleService;

    public List<GeneralStateDto> getAll(Pageable pageable){
        return generalStateRepository.findAll(pageable)
                .stream()
                .map(generalStateMapper::toDto)
                .toList();
    }

    public List<GeneralStateDto> getAllForTicket(Pageable pageable){
        return generalStateRepository.findAllByIsForTicketIsTrue(pageable)
                .stream()
                .map(generalStateMapper::toDto)
                .toList();
    }

    public List<GeneralStateDto> getAllForCi(Pageable pageable){
        return generalStateRepository.findAllByIsForCiTrue(pageable)
                .stream()
                .map(generalStateMapper::toDto)
                .toList();
    }

    public List<GeneralStateDto> getAllForCloseState(Pageable pageable){
        return generalStateRepository.findAllByIsForCloseStateTrue(pageable)
                .stream()
                .map(generalStateMapper::toDto)
                .toList();
    }

    public GeneralStateDto getOne(Long id) {
        return generalStateMapper.toDto(generalStateRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, GENERAL_STATE_NOT_FOUND_MESSAGE))
        );
    }

    public GeneralStateDto newItem(GeneralStateDto generalStateDto) {
        GeneralState generalState = generalStateMapper.toEntity(generalStateDto);
        return generalStateMapper.toDto(generalStateRepository.save(generalState));
    }

    public GeneralStateDto editItem(GeneralStateDto generalStateDto, Long id) {
        if (!generalStateRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, GENERAL_STATE_NOT_FOUND_MESSAGE);
        }
        GeneralState generalState = generalStateMapper.toEntity(generalStateDto);
        generalState.setId(id);
        return generalStateMapper.toDto(generalStateRepository.save(generalState));
    }

    public void deleteItem(Long id) {
        if (!generalStateRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, GENERAL_STATE_NOT_FOUND_MESSAGE);
        }
    }
}
