package cz.patyk.invoicesystem_be.convertor;

import cz.patyk.invoicesystem_be.dto.in.QueueUserDtoIn;
import cz.patyk.invoicesystem_be.dto.out.QueueUserDtoOut;
import cz.patyk.invoicesystem_be.entities.QueueUser;
import cz.patyk.invoicesystem_be.mapper.QueueUserMapper;
import cz.patyk.invoicesystem_be.service.QueueServices;
import cz.patyk.invoicesystem_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueUserConvertor implements CrudConvertor<QueueUserDtoIn, QueueUserDtoOut, QueueUser> {
    private final UserService userService;
    private final QueueServices queueServices;
    private final QueueUserMapper queueUserMapper;

    @Override
    public QueueUser inputToEntity(QueueUserDtoIn userInput) {
        var entity = queueUserMapper.toEntity(userInput);
        entity.setUser(userService.getOneEntity(userInput.getUser()));
        entity.setQueue(queueServices.getOneEntity(userInput.getUser()));
        return entity;
    }

    @Override
    public QueueUserDtoOut entityToDto(QueueUser entity) {
        return queueUserMapper.toDto(entity);
    }
}
