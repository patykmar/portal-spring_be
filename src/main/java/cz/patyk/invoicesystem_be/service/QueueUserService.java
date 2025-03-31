package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.convertor.QueueUserConvertor;
import cz.patyk.invoicesystem_be.dto.in.QueueUserDtoIn;
import cz.patyk.invoicesystem_be.dto.out.QueueUserDtoOut;
import cz.patyk.invoicesystem_be.entities.QueueUser;
import cz.patyk.invoicesystem_be.repositories.QueueUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueUserService implements CrudService<QueueUserDtoIn, QueueUserDtoOut, QueueUser> {
    private final QueueUserConvertor queueUserConvertor;
    private final QueueUserRepository queueUserRepository;
    private final ErrorHandleService errorHandleService;

    @Override
    public List<QueueUserDtoOut> getAll(Pageable pageable) {
        return queueUserRepository.findAll(pageable)
                .stream()
                .map(queueUserConvertor::entityToDto)
                .toList();
    }

    @Override
    public QueueUserDtoOut getOne(Long id) {
        return queueUserConvertor.entityToDto(getOneEntity(id));
    }

    @Override
    public QueueUser getOneEntity(Long id) {
        return queueUserRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, ServiceConstants.QUEUE_USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public QueueUserDtoOut newItem(QueueUserDtoIn dtoIn) {
        var queueUser = queueUserConvertor.inputToEntity(dtoIn);
        return queueUserConvertor.entityToDto(queueUserRepository.save(queueUser));
    }

    @Override
    public QueueUserDtoOut editItem(QueueUserDtoIn dtoIn, Long id) {
        var queueUserFromDb = getOneEntity(id);
        var queueUser = queueUserConvertor.inputToEntity(dtoIn);
        queueUser.setId(id);
        return queueUserConvertor.entityToDto(queueUserRepository.save(queueUser));
    }

    @Override
    public void deleteItem(Long id) {
        var queueUserFromDb = getOneEntity(id);
        queueUserRepository.delete(queueUserFromDb);
    }
}
