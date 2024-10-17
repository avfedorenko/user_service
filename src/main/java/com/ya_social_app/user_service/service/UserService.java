package com.ya_social_app.user_service.service;

import com.ya_social_app.user_service.dto.UserDto;
import com.ya_social_app.user_service.entity.User;
import com.ya_social_app.user_service.mapper.UserMapper;
import com.ya_social_app.user_service.repository.UserRepository;
import com.ya_social_app.user_service.validator.UserValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Transactional
    public UserDto createUser(UserDto userDto) {
        userValidator.validateCreatedUser(userDto);
        User user = userMapper.toEntity(userDto);
        User createdUser = userRepository.save(user);
        log.info("Created user: {}", createdUser);
        return userMapper.toDto(createdUser);
    }

    public UserDto getUserById(Long id) {
        User requestedUser = getUserEntityById(id);
        log.debug("Got user: {}", requestedUser);
        return userMapper.toDto(requestedUser);
    }

    public User getUserEntityById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            log.debug("User not found with id: {}", id);
            throw new EntityNotFoundException(String.format("User not found with id: %s", id));
        } else {
            User user = userOptional.get();
            log.debug("Got user from database: {}", user);
            return user;
        }
    }
}
