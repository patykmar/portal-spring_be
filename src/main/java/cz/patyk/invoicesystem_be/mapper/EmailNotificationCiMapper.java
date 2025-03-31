package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.EmailNotificationCiDtoIn;
import cz.patyk.invoicesystem_be.dto.out.EmailNotificationCiDtoOut;
import cz.patyk.invoicesystem_be.entities.Ci;
import cz.patyk.invoicesystem_be.entities.EmailNotificationCi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {CiMapper.class})
public interface EmailNotificationCiMapper {
    @Mapping(target = "id", source = "emailNotificationCiDto.id")
    @Mapping(target = "emailAddress", source = "emailNotificationCiDto.emailAddress")
    @Mapping(target = "ci", source = "ci")
    EmailNotificationCi toEntity(EmailNotificationCiDtoIn emailNotificationCiDto, Ci ci);

    @Mapping(target = "ci", source = "ci")
    EmailNotificationCiDtoOut toDto(EmailNotificationCi emailNotificationCi);
}
