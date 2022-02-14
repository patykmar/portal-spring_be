package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.QueueUserDto;
import cz.patyk.invoicesystem_be.entities.QueueUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QueueUserMapper {
    QueueUserDto toDto(QueueUser queueUser);

    QueueUser toEntity(QueueUserDto queueUserDto);
}
