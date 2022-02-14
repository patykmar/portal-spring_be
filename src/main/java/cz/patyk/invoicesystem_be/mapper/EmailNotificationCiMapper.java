package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.EmailNotificationCiDto;
import cz.patyk.invoicesystem_be.entities.EmailNotificationCi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailNotificationCiMapper {
    EmailNotificationCi toEntity(EmailNotificationCiDto emailNotificationCiDto);

    EmailNotificationCiDto toDto(EmailNotificationCi emailNotificationCi);
}
