package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.UserDtoIn;
import cz.patyk.invoicesystem_be.dto.out.UserDtoOut;
import cz.patyk.invoicesystem_be.entities.Company;
import cz.patyk.invoicesystem_be.entities.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static cz.patyk.invoicesystem_be.common.TestConstants.COMPANY_TEST_ENTITY;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_CREATED;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_EMAIL;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_FIRST_NAME;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_LAST_LOGIN;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_LAST_NAME;
import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_PASSWORD_CHANGED;
import static org.apache.commons.lang3.math.NumberUtils.LONG_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;
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
    @MethodSource("dtoProvider")
    void toEntity(UserDtoIn userDtoIn) {
        assertThat(USER_MAPPER.toEntity(userDtoIn))
                .returns(userDtoIn.getId(), User::getId)
                .returns(userDtoIn.getEmail(), User::getEmail)
                .returns(userDtoIn.getFirstName(), User::getFirstName)
                .returns(userDtoIn.getLastName(), User::getLastName)
                .returns(Enum.valueOf(User.Role.class, userDtoIn.getRoles()), User::getRoles);

        assertThat(USER_MAPPER.toEntity(userDtoIn).getEmployeeOfCompanyId())
                .returns(userDtoIn.getEmployeeOfCompanyId(), Company::getId);
    }

    private static Stream<Arguments> entityProvider() {
        return Stream.of(
                Arguments.of(User.builder().id(Long.MIN_VALUE).email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME).lastName(USER_TEST_LAST_NAME).lastLogin(USER_TEST_LAST_LOGIN).createdDate(USER_TEST_CREATED).passwordChanged(USER_TEST_PASSWORD_CHANGED).employeeOfCompanyId(COMPANY_TEST_ENTITY).roles(User.Role.ADMIN).build()),
                Arguments.of(User.builder().id(LONG_MINUS_ONE).email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME).lastName(USER_TEST_LAST_NAME).lastLogin(USER_TEST_LAST_LOGIN).createdDate(USER_TEST_CREATED).passwordChanged(USER_TEST_PASSWORD_CHANGED).employeeOfCompanyId(COMPANY_TEST_ENTITY).roles(User.Role.USER).build()),
                Arguments.of(User.builder().id(LONG_ZERO).email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME).lastName(USER_TEST_LAST_NAME).lastLogin(USER_TEST_LAST_LOGIN).createdDate(USER_TEST_CREATED).passwordChanged(USER_TEST_PASSWORD_CHANGED).employeeOfCompanyId(COMPANY_TEST_ENTITY).roles(User.Role.ADMIN).build()),
                Arguments.of(User.builder().id(LONG_ONE).email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME).lastName(USER_TEST_LAST_NAME).lastLogin(USER_TEST_LAST_LOGIN).createdDate(USER_TEST_CREATED).passwordChanged(USER_TEST_PASSWORD_CHANGED).employeeOfCompanyId(COMPANY_TEST_ENTITY).roles(User.Role.USER).build()),
                Arguments.of(User.builder().id(Long.MAX_VALUE).email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME).lastName(USER_TEST_LAST_NAME).lastLogin(USER_TEST_LAST_LOGIN).createdDate(USER_TEST_CREATED).passwordChanged(USER_TEST_PASSWORD_CHANGED).employeeOfCompanyId(COMPANY_TEST_ENTITY).roles(User.Role.ADMIN).build())
        );
    }

    private static Stream<Arguments> dtoProvider() {
        return Stream.of(
                Arguments.of(UserDtoIn.builder().id(Long.MIN_VALUE).email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME).lastName(USER_TEST_LAST_NAME).employeeOfCompanyId(LONG_ONE).roles("ADMIN").build()),
                Arguments.of(UserDtoIn.builder().id(LONG_MINUS_ONE).email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME).lastName(USER_TEST_LAST_NAME).employeeOfCompanyId(LONG_ONE).roles("ADMIN").build()),
                Arguments.of(UserDtoIn.builder().id(LONG_ZERO).email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME).lastName(USER_TEST_LAST_NAME).employeeOfCompanyId(LONG_ONE).roles("ADMIN").build()),
                Arguments.of(UserDtoIn.builder().id(LONG_ONE).email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME).lastName(USER_TEST_LAST_NAME).employeeOfCompanyId(LONG_ONE).roles("ADMIN").build()),
                Arguments.of(UserDtoIn.builder().id(Long.MAX_VALUE).email(USER_TEST_EMAIL).firstName(USER_TEST_FIRST_NAME).lastName(USER_TEST_LAST_NAME).employeeOfCompanyId(LONG_ONE).roles("ADMIN").build())
        );
    }
}