package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.UserDto;
import cz.patyk.invoicesystem_be.dto.in.UserPasswordChangeIn;
import cz.patyk.invoicesystem_be.entities.User;
import cz.patyk.invoicesystem_be.mapper.UserMapper;
import cz.patyk.invoicesystem_be.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static cz.patyk.invoicesystem_be.service.ServiceConstants.USER_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ErrorHandleService errorHandleService;

    public List<UserDto> getAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto getOne(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, USER_NOT_FOUND_MESSAGE))
        );
    }

    public UserDto newItem(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto editItem(UserDto userDto, Long id) {
        isIdExist(id);
        User user = userMapper.toEntity(userDto);
        user.setId(id);
        return userMapper.toDto(userRepository.save(user));
    }

    public void passwordChange(UserPasswordChangeIn userPasswordChangeIn, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, USER_NOT_FOUND_MESSAGE));

        if(!userPasswordChangeIn.getNewPassword().equals(userPasswordChangeIn.getReTypedPassword())){
            throw errorHandleService.handleBadRequestError("New password and re-typed password is not match.");
        }

        if (!passwordEncoder.matches(userPasswordChangeIn.getOldPassword(), user.getPassword())) {
            throw errorHandleService.handleBadRequestError(id, "Your old password is incorrect.");
        } else {
            changePassword(user, userPasswordChangeIn.getNewPassword());
        }
    }

    public String passwordEncode(String clearPassword) {
        return passwordEncoder.encode(clearPassword);
    }

    public void deleteItem(Long id) {
        isIdExist(id);
        userRepository.deleteById(id);
    }

    private void isIdExist(Long id) {
        if (!userRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, USER_NOT_FOUND_MESSAGE);
        }
    }

    private void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordChanged(Instant.now().getEpochSecond());
        userRepository.save(user);
    }
}
