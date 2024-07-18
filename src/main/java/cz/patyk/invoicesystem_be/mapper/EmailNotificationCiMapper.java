package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.EmailNotificationCiDto;
import cz.patyk.invoicesystem_be.entities.EmailNotificationCi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmailNotificationCiMapper {
    @Mapping(target = "ci", ignore = true)
    EmailNotificationCi toEntity(EmailNotificationCiDto emailNotificationCiDto);

    @Mapping(target = "ciId", ignore = true)
    EmailNotificationCiDto toDto(EmailNotificationCi emailNotificationCi);
}
