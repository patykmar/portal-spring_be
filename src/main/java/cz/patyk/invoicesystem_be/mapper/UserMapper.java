package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.UserDto;
import cz.patyk.invoicesystem_be.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}
