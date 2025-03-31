package cz.patyk.invoicesystem_be.convertor;

import cz.patyk.invoicesystem_be.dto.in.EmailNotificationCiDtoIn;
import cz.patyk.invoicesystem_be.dto.out.EmailNotificationCiDtoOut;
import cz.patyk.invoicesystem_be.entities.EmailNotificationCi;
import cz.patyk.invoicesystem_be.mapper.EmailNotificationCiMapper;
import cz.patyk.invoicesystem_be.service.CiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailNotificationCiConvertor implements CrudConvertor<EmailNotificationCiDtoIn, EmailNotificationCiDtoOut, EmailNotificationCi> {
    private final EmailNotificationCiMapper emailNotificationCiMapper;
    private final CiService ciService;

    @Override
    public EmailNotificationCi inputToEntity(EmailNotificationCiDtoIn userInput) {
        var ci = ciService.getOneEntity(userInput.getCiId());
        return emailNotificationCiMapper.toEntity(userInput, ci);
    }

    @Override
    public EmailNotificationCiDtoOut entityToDto(EmailNotificationCi entity) {
        return emailNotificationCiMapper.toDto(entity);
    }
}
