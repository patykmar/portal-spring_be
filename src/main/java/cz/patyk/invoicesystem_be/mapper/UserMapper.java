package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.UserDto;
import cz.patyk.invoicesystem_be.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface UserMapper {

    @Mapping(target = "employeeOfCompanyId",  source= "employeeOfCompanyId.id")
    UserDto toDto(User user);

    @Mapping(target = "employeeOfCompanyId.id", source = "employeeOfCompanyId")
    User toEntity(UserDto userDto);
}
