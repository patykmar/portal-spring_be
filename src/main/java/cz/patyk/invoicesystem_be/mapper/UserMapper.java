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
    User toEntity(UserDtoIn userDtoIn);

//    @Mapping(target = "employeeOfCompanyId",  source= "employeeOfCompanyId.id")
    UserDtoIn toDtoIn(UserDtoInTwoPassword userDtoInTwoPassword);
}
