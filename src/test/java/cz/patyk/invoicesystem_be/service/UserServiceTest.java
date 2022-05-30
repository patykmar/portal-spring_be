package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.constant.TestDtos;
import cz.patyk.invoicesystem_be.constant.TestEntities;
import cz.patyk.invoicesystem_be.dto.in.UserPasswordChangeIn;
import cz.patyk.invoicesystem_be.dto.out.UserDtoOut;
import cz.patyk.invoicesystem_be.entities.User;
import cz.patyk.invoicesystem_be.exceptions.ApplicationException;
import cz.patyk.invoicesystem_be.mapper.UserMapper;
import cz.patyk.invoicesystem_be.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class UserServiceTest {
    private static BCryptPasswordEncoder bCryptPasswordEncoder;
    private static UserService userService;
    private static UserRepository userRepository;

    @BeforeAll
    static void initTest() {
        userRepository = Mockito.mock(UserRepository.class);
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ErrorHandleService errorHandleService = new ErrorHandleService();

        userService = new UserService(userRepository, userMapper, bCryptPasswordEncoder, errorHandleService);
    }

    @Test
    void passwordEncode() {
        String passwordEncode = userService.passwordEncode(Common.USER_TEST_PASSWORD_1);
        log.debug("Password encode: {}", passwordEncode);
        assertTrue(bCryptPasswordEncoder.matches(Common.USER_TEST_PASSWORD_1, passwordEncode));
    }

    @Test
    void passwordChangeCorrect() {
        Mockito
                .when(userRepository.findById(NumberUtils.LONG_ONE))
                .thenReturn(Optional.ofNullable(TestEntities.USER_ENTITY));
        Mockito
                .when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(response -> response.getArguments()[0]);

        Assertions.assertThat(userService.passwordChange(TestDtos.USER_PASSWORD_CHANGE_CORRECT, NumberUtils.LONG_ONE))
                .returns(TestEntities.USER_ENTITY.getId(), UserDtoOut::getId);
    }

    @ParameterizedTest
    @MethodSource("passwordChangeProvideWrongPasswords")
    void passwordChangeWithWrongPassword(UserPasswordChangeIn userPasswordChangeIn, String errorMessage) {
        Mockito
                .when(userRepository.findById(NumberUtils.LONG_ONE))
                .thenReturn(Optional.ofNullable(TestEntities.USER_ENTITY));

        Assertions.assertThatThrownBy(() -> userService.passwordChange(userPasswordChangeIn, NumberUtils.LONG_ONE))
                .isInstanceOf(ApplicationException.class)
                .hasMessageContaining(errorMessage);
    }

    private static Stream<Arguments> passwordChangeProvideWrongPasswords() {
        return Stream.of(
                Arguments.of(TestDtos.USER_PASSWORD_CHANGE_IN_WRONG_OLD_PASSWORD, ServiceConstants.USER_INCORRECT_OLD_PASSWORD),
                Arguments.of(TestDtos.USER_PASSWORD_CHANGE_IN_WRONG_RETYPED_PASSWORD, ServiceConstants.USER_PASSWORD_AND_RETYPED_PASSWORD_NOT_MATCH)
        );
    }
}