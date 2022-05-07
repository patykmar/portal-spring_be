package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.UserDtoIn;
import cz.patyk.invoicesystem_be.dto.in.UserDtoInTwoPassword;
import cz.patyk.invoicesystem_be.dto.out.UserDtoOut;
import cz.patyk.invoicesystem_be.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface UserMapper {

    @Mapping(target = "employeeOfCompanyId",  source= "employeeOfCompanyId.id")
    UserDtoOut toDto(User user);

    @Mapping(target = "employeeOfCompanyId.id", source = "employeeOfCompanyId")
    @Mapping(target = "roles", expression = "java(Enum.valueOf(User.Role.class, userDtoIn.getRoles().toUpperCase()))")
    User toEntity(UserDtoIn userDtoIn);

    @Mapping(target = "employeeOfCompanyId.id", source = "employeeOfCompanyId")
    @Mapping(target = "roles", expression = "java(Enum.valueOf(User.Role.class, userDtoInTwoPassword.getRoles().toUpperCase()))")
    @Mapping(target = "lastLogin", expression = "java(Long.valueOf(\"0\"))")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now().getEpochSecond())")
    @Mapping(target = "passwordChanged", expression = "java(java.time.Instant.now().getEpochSecond())")
    User toEntity(UserDtoInTwoPassword userDtoInTwoPassword);
}
