package com.ya_social_app.user_service.validator;

import com.ya_social_app.user_service.dto.UserDto;
import com.ya_social_app.user_service.exception.DataValidationException;
import com.ya_social_app.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void validateCreatedUser(UserDto userDto) {
        if (userDto.getId() != null) {
            throw new DataValidationException("id", "Id should be null for created user");
        }
        validateUsername(userDto);
        validateEmail(userDto);
    }

    private void validateUsername(UserDto userDto) {
        if (userRepository.existsUserByUsername(userDto.getUsername())) {
            throw new DataValidationException("username", "Username is already taken");
        }
    }

    private void validateEmail(UserDto userDto) {
        if (userRepository.existsUserByEmail(userDto.getEmail())) {
            throw new DataValidationException("email", "Email is already taken");
        }
    }
}
