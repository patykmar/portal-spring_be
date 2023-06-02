package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.QueueDto;
import cz.patyk.invoicesystem_be.entities.Queue;
import cz.patyk.invoicesystem_be.exceptions.ApplicationException;
import cz.patyk.invoicesystem_be.mapper.QueueMapper;
import cz.patyk.invoicesystem_be.repositories.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.patyk.invoicesystem_be.service.ServiceConstants.QUEUE_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class QueueServices implements CrudService<QueueDto, QueueDto, Queue> {
    private final QueueRepository queueRepository;
    private final QueueMapper queueMapper;
    private final ErrorHandleService errorHandleService;

    @Override
    public List<QueueDto> getAll(Pageable pageable) {
        return queueRepository.findAll(pageable)
                .stream()
                .map(queueMapper::toDto)
                .toList();
    }

    @Override
    public QueueDto getOne(Long id) {
        return queueMapper.toDto(getOneEntity(id));
    }

    @Override
    public Queue getOneEntity(Long id) {
        return queueRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, QUEUE_NOT_FOUND_MESSAGE));
    }

    @Override
    public QueueDto newItem(QueueDto queueDto) {
        Queue queue = queueMapper.toEntity(queueDto);
        return queueMapper.toDto(queueRepository.save(queue));
    }

    @Override
    public QueueDto editItem(QueueDto queueDto, Long id) {
        checkIfExist(id);
        Queue queue = queueMapper.toEntity(queueDto);
        queue.setId(id);
        return queueMapper.toDto(queueRepository.save(queue));
    }

    @Override
    public void deleteItem(Long id) {
        checkIfExist(id);
        queueRepository.deleteById(id);
    }

    private void checkIfExist(Long id) throws ApplicationException {
        if (!queueRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, ServiceConstants.QUEUE_NOT_FOUND_MESSAGE);
        }
    }
}
