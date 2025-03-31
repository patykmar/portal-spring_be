package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.convertor.EmailNotificationCiConvertor;
import cz.patyk.invoicesystem_be.dto.in.EmailNotificationCiDtoIn;
import cz.patyk.invoicesystem_be.dto.out.EmailNotificationCiDtoOut;
import cz.patyk.invoicesystem_be.entities.EmailNotificationCi;
import cz.patyk.invoicesystem_be.repositories.EmailNotificationCiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailNotificationCiService implements CrudService<EmailNotificationCiDtoIn, EmailNotificationCiDtoOut, EmailNotificationCi> {
    private final EmailNotificationCiRepository emailNotificationCiRepository;
    private final EmailNotificationCiConvertor emailNotificationCiConvertor;
    private final ErrorHandleService errorHandleService;

    @Override
    public List<EmailNotificationCiDtoOut> getAll(Pageable pageable) {
        return emailNotificationCiRepository.findAll(pageable)
                .stream()
                .map(emailNotificationCiConvertor::entityToDto)
                .toList();
    }

    @Override
    public EmailNotificationCiDtoOut getOne(Long id) {
        return emailNotificationCiConvertor.entityToDto(getOneEntity(id));
    }

    @Override
    public EmailNotificationCi getOneEntity(Long id) {
        return emailNotificationCiRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, ServiceConstants.EMAIL_NOTIFICATION_CI_NOT_FOUND_MESSAGE));
    }

    @Override
    public EmailNotificationCiDtoOut newItem(EmailNotificationCiDtoIn dtoIn) {
        return emailNotificationCiConvertor.entityToDto(
                emailNotificationCiRepository.save(emailNotificationCiConvertor.inputToEntity(dtoIn))
        );
    }

    @Override
    public EmailNotificationCiDtoOut editItem(EmailNotificationCiDtoIn dtoIn, Long id) {
        var entityById = getOneEntity(id);
        var entityFromDto = emailNotificationCiConvertor.inputToEntity(dtoIn);
        entityFromDto.setId(entityById.getId());
        return emailNotificationCiConvertor.entityToDto(emailNotificationCiRepository.save(entityFromDto));
    }

    @Override
    public void deleteItem(Long id) {
        var entityById = getOneEntity(id);
        emailNotificationCiRepository.delete(entityById);
    }
}
