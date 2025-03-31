package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.QueueUserDtoIn;
import cz.patyk.invoicesystem_be.dto.out.QueueUserDtoOut;
import cz.patyk.invoicesystem_be.entities.QueueUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {UserMapper.class, QueueMapper.class})
public interface QueueUserMapper {

    @Mapping(target = "queueDto", source = "queue")
    @Mapping(target = "userDto", source = "user")
    QueueUserDtoOut toDto(QueueUser queueUser);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "queue", ignore = true)
    QueueUser toEntity(QueueUserDtoIn queueUserDto);
}
