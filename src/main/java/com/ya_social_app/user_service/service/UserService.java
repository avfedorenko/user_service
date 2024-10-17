package com.ya_social_app.user_service.service;

import com.ya_social_app.user_service.dto.UserDto;
import com.ya_social_app.user_service.entity.User;
import com.ya_social_app.user_service.mapper.UserMapper;
import com.ya_social_app.user_service.repository.UserRepository;
import com.ya_social_app.user_service.validator.UserValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    @Transactional
    public UserDto createUser(UserDto userDto) {
        userValidator.validateCreatedUser(userDto);
        User user = userMapper.toEntity(userDto);
        User createdUser = userRepository.save(user);
        return userMapper.toDto(createdUser);
    }

    public UserDto getUserById(Long id) {
        User requestedUser = getUserEntityById(id);
        return userMapper.toDto(requestedUser);
    }

    public User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", id)));
    }
}
