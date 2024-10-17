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
        validateUsername(userDto);
    }

    private void validateUsername(UserDto userDto) {
        if (userRepository.existsUserByUsername(userDto.getUsername())){
            throw new DataValidationException("Username is already taken");
        }
    }


}
