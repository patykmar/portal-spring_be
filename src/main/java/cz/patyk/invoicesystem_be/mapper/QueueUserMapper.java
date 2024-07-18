package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.QueueUserDto;
import cz.patyk.invoicesystem_be.entities.QueueUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface QueueUserMapper {
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "queueId", ignore = true)
    QueueUserDto toDto(QueueUser queueUser);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "queue", ignore = true)
    QueueUser toEntity(QueueUserDto queueUserDto);
}
