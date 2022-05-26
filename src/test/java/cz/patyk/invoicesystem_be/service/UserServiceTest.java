package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.mapper.UserMapper;
import cz.patyk.invoicesystem_be.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_PASSWORD;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class UserServiceTest {
    private static BCryptPasswordEncoder bCryptPasswordEncoder;
    private static UserService userService;

    @BeforeAll
    static void initTest(){
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ErrorHandleService errorHandleService = Mockito.mock(ErrorHandleService.class);

        userService = new UserService(userRepository, userMapper, bCryptPasswordEncoder, errorHandleService);
    }

    @Test
    void passwordEncode() {
        String passwordEncode = userService.passwordEncode(USER_TEST_PASSWORD);
        log.info("Password encode: {}", passwordEncode);
        assertTrue(bCryptPasswordEncoder.matches(USER_TEST_PASSWORD, passwordEncode));
    }
}