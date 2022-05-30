package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.constant.TestEntities;
import cz.patyk.invoicesystem_be.dto.in.UserDtoIn;
import cz.patyk.invoicesystem_be.dto.in.UserDtoInTwoPassword;
import cz.patyk.invoicesystem_be.dto.out.UserDtoOut;
import cz.patyk.invoicesystem_be.entities.Company;
import cz.patyk.invoicesystem_be.entities.User;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {
    private static final UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @ParameterizedTest
    @MethodSource("entityProvider")
    void toDto(User user) {
        assertThat(USER_MAPPER.toDto(user))
                .returns(user.getId(), UserDtoOut::getId)
                .returns(user.getEmail(), UserDtoOut::getEmail)
                .returns(user.getFirstName(), UserDtoOut::getFirstName)
                .returns(user.getLastName(), UserDtoOut::getLastName)
                .returns(user.getLastLogin(), UserDtoOut::getLastLogin)
                .returns(user.getCreatedDate(), UserDtoOut::getCreatedDate)
                .returns(user.getPasswordChanged(), UserDtoOut::getPasswordChanged)
                .returns(user.getRoles().toString(), UserDtoOut::getRoles);
    }

    @ParameterizedTest
    @MethodSource("dtoInProvider")
    void toEntity(UserDtoIn userDtoIn) {
        assertThat(USER_MAPPER.toEntity(userDtoIn))
                .returns(userDtoIn.getId(), User::getId)
                .returns(userDtoIn.getEmail(), User::getEmail)
                .returns(userDtoIn.getFirstName(), User::getFirstName)
                .returns(userDtoIn.getLastName(), User::getLastName)
                .returns(Enum.valueOf(User.Role.class, userDtoIn.getRoles().toUpperCase()), User::getRoles);

        assertThat(USER_MAPPER.toEntity(userDtoIn).getEmployeeOfCompanyId())
                .returns(userDtoIn.getEmployeeOfCompanyId(), Company::getId);
    }

    @ParameterizedTest
    @MethodSource("dtoInTwoPasswordProvider")
    void toEntityFromTwoPassword(UserDtoInTwoPassword userDtoInTwoPassword) {
        assertThat(USER_MAPPER.toEntity(userDtoInTwoPassword))
                .returns(userDtoInTwoPassword.getId(), User::getId)
                .returns(userDtoInTwoPassword.getEmail(), User::getEmail)
                .returns(userDtoInTwoPassword.getFirstName(), User::getFirstName)
                .returns(userDtoInTwoPassword.getLastName(), User::getLastName)
                .returns(Enum.valueOf(User.Role.class, userDtoInTwoPassword.getRoles().toUpperCase()), User::getRoles)
                .returns(NumberUtils.LONG_ZERO, User::getLastLogin);

        assertThat(USER_MAPPER.toEntity(userDtoInTwoPassword).getEmployeeOfCompanyId())
                .returns(userDtoInTwoPassword.getEmployeeOfCompanyId(), Company::getId);
    }

    private static Stream<Arguments> entityProvider() {
        return Stream.of(
                Arguments.of(User.builder().id(Long.MIN_VALUE).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).lastLogin(Common.USER_TEST_LAST_LOGIN).createdDate(Common.USER_TEST_CREATED).passwordChanged(Common.USER_TEST_PASSWORD_CHANGED).employeeOfCompanyId(TestEntities.COMPANY_TEST_ENTITY).roles(User.Role.ADMIN).build()),
                Arguments.of(User.builder().id(NumberUtils.LONG_MINUS_ONE).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).lastLogin(Common.USER_TEST_LAST_LOGIN).createdDate(Common.USER_TEST_CREATED).passwordChanged(Common.USER_TEST_PASSWORD_CHANGED).employeeOfCompanyId(TestEntities.COMPANY_TEST_ENTITY).roles(User.Role.USER).build()),
                Arguments.of(User.builder().id(NumberUtils.LONG_ZERO).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).lastLogin(Common.USER_TEST_LAST_LOGIN).createdDate(Common.USER_TEST_CREATED).passwordChanged(Common.USER_TEST_PASSWORD_CHANGED).employeeOfCompanyId(TestEntities.COMPANY_TEST_ENTITY).roles(User.Role.ADMIN).build()),
                Arguments.of(User.builder().id(NumberUtils.LONG_ONE).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).lastLogin(Common.USER_TEST_LAST_LOGIN).createdDate(Common.USER_TEST_CREATED).passwordChanged(Common.USER_TEST_PASSWORD_CHANGED).employeeOfCompanyId(TestEntities.COMPANY_TEST_ENTITY).roles(User.Role.USER).build()),
                Arguments.of(User.builder().id(Long.MAX_VALUE).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).lastLogin(Common.USER_TEST_LAST_LOGIN).createdDate(Common.USER_TEST_CREATED).passwordChanged(Common.USER_TEST_PASSWORD_CHANGED).employeeOfCompanyId(TestEntities.COMPANY_TEST_ENTITY).roles(User.Role.ADMIN).build())
        );
    }

    private static Stream<Arguments> dtoInProvider() {
        return Stream.of(
                Arguments.of(UserDtoIn.builder().id(Long.MIN_VALUE).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).employeeOfCompanyId(NumberUtils.LONG_ONE).roles("admin").build()),
                Arguments.of(UserDtoIn.builder().id(NumberUtils.LONG_MINUS_ONE).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).employeeOfCompanyId(NumberUtils.LONG_ONE).roles("admin").build()),
                Arguments.of(UserDtoIn.builder().id(NumberUtils.LONG_ZERO).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).employeeOfCompanyId(NumberUtils.LONG_ONE).roles("admin").build()),
                Arguments.of(UserDtoIn.builder().id(NumberUtils.LONG_ONE).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).employeeOfCompanyId(NumberUtils.LONG_ONE).roles("admin").build()),
                Arguments.of(UserDtoIn.builder().id(Long.MAX_VALUE).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).employeeOfCompanyId(NumberUtils.LONG_ONE).roles("admin").build())
        );
    }

    private static Stream<Arguments> dtoInTwoPasswordProvider() {
        return Stream.of(
                Arguments.of(UserDtoInTwoPassword.builder().id(Long.MIN_VALUE).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).employeeOfCompanyId(NumberUtils.LONG_ONE).roles("admin").password(Common.USER_TEST_PASSWORD_1).retypePassword(Common.USER_TEST_PASSWORD_1).build()),
                Arguments.of(UserDtoInTwoPassword.builder().id(NumberUtils.LONG_MINUS_ONE).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).employeeOfCompanyId(NumberUtils.LONG_ONE).roles("admin").password(Common.USER_TEST_PASSWORD_1).retypePassword(Common.USER_TEST_PASSWORD_1).build()),
                Arguments.of(UserDtoInTwoPassword.builder().id(NumberUtils.LONG_ZERO).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).employeeOfCompanyId(NumberUtils.LONG_ONE).roles("admin").password(Common.USER_TEST_PASSWORD_1).retypePassword(Common.USER_TEST_PASSWORD_1).build()),
                Arguments.of(UserDtoInTwoPassword.builder().id(NumberUtils.LONG_ONE).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).employeeOfCompanyId(NumberUtils.LONG_ONE).roles("admin").password(Common.USER_TEST_PASSWORD_1).retypePassword(Common.USER_TEST_PASSWORD_1).build()),
                Arguments.of(UserDtoInTwoPassword.builder().id(Long.MAX_VALUE).email(Common.USER_TEST_EMAIL).firstName(Common.USER_TEST_FIRST_NAME).lastName(Common.USER_TEST_LAST_NAME).employeeOfCompanyId(NumberUtils.LONG_ONE).roles("admin").password(Common.USER_TEST_PASSWORD_1).retypePassword(Common.USER_TEST_PASSWORD_1).build())
        );
    }
}