package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.in.UserDtoIn;
import cz.patyk.invoicesystem_be.dto.in.UserDtoInTwoPassword;
import cz.patyk.invoicesystem_be.dto.out.UserDtoOut;
import cz.patyk.invoicesystem_be.dto.in.UserPasswordChangeIn;
import cz.patyk.invoicesystem_be.entities.User;
import cz.patyk.invoicesystem_be.mapper.UserMapper;
import cz.patyk.invoicesystem_be.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ErrorHandleService errorHandleService;

    public List<UserDtoOut> getAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDtoOut getOne(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, ServiceConstants.USER_NOT_FOUND_MESSAGE))
        );
    }

    public UserDtoOut newItem(UserDtoInTwoPassword userDtoInTwoPassword) {
        if (!userDtoInTwoPassword.getPassword().equals(userDtoInTwoPassword.getRetypePassword())) {
            throw errorHandleService.handleBadRequestError(ServiceConstants.USER_PASSWORD_AND_RETYPED_PASSWORD_NOT_MATCH);
        }
        User user = userMapper.toEntity(userDtoInTwoPassword);
        user.setPassword(passwordEncode(userDtoInTwoPassword.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }

    public UserDtoOut editItem(UserDtoIn userDtoIn, Long id) {
        User userFromDb = userRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, ServiceConstants.USER_NOT_FOUND_MESSAGE));

        User user = userMapper.toEntity(userDtoIn);
        user.setId(id);
        user.setLastLogin(userFromDb.getLastLogin());
        user.setCreatedDate(userFromDb.getCreatedDate());
        user.setPasswordChanged(userFromDb.getPasswordChanged());

        return userMapper.toDto(userRepository.save(user));
    }

    public UserDtoOut passwordChange(UserPasswordChangeIn userPasswordChangeIn, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, ServiceConstants.USER_NOT_FOUND_MESSAGE));

        if (!userPasswordChangeIn.getNewPassword().equals(userPasswordChangeIn.getReTypedPassword())) {
            throw errorHandleService.handleBadRequestError(ServiceConstants.USER_PASSWORD_AND_RETYPED_PASSWORD_NOT_MATCH);
        }

        if (!bCryptPasswordEncoder.matches(userPasswordChangeIn.getOldPassword(), user.getPassword())) {
            throw errorHandleService.handleBadRequestError(id, ServiceConstants.USER_INCORRECT_OLD_PASSWORD);
        } else {
            changePassword(user, userPasswordChangeIn.getNewPassword());
        }
        return userMapper.toDto(user);
    }

    public String passwordEncode(String clearPassword) {
        return bCryptPasswordEncoder.encode(clearPassword);
    }

    public void deleteItem(Long id) {
        isIdExist(id);
        userRepository.deleteById(id);
    }

    private void isIdExist(Long id) {
        if (!userRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, ServiceConstants.USER_NOT_FOUND_MESSAGE);
        }
    }

    private void changePassword(User user, String newPassword) {
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        user.setPasswordChanged(Instant.now().getEpochSecond());
        userRepository.save(user);
    }
}
