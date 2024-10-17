package com.ya_social_app.user_service.service;

import com.ya_social_app.user_service.dto.UserDto;
import com.ya_social_app.user_service.entity.User;
import com.ya_social_app.user_service.mapper.UserMapperImpl;
import com.ya_social_app.user_service.repository.UserRepository;
import com.ya_social_app.user_service.validator.UserValidator;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Spy
    private UserMapperImpl userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator userValidator;

    private UserDto createdUserDto;
    private UserDto existedUserDto;
    private User existedUserEntity;

    @BeforeEach
    void setUp() {
        createdUserDto = UserDto.builder().username("username").email("email@email.com").about("About").build();
        existedUserDto = UserDto.builder().id(1L).username("username").email("email@email.com").about("About").build();
        existedUserEntity = User.builder().id(1L).username("username").email("email@email.com").about("About").build();
    }

    @Test
    void testCreateUser() {
        userService.createUser(createdUserDto);

        verify(userValidator, times(1)).validateCreatedUser(createdUserDto);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserByIdSuccessful() {
        when(userRepository.findById(existedUserDto.getId())).thenReturn(Optional.of(existedUserEntity));
        assertDoesNotThrow(() -> userService.getUserById(existedUserDto.getId()));

    }

    @Test
    void testGetUserByIdFailed() {
        when(userRepository.findById(existedUserDto.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getUserById(existedUserDto.getId()));
    }

    @Test
    void testGetUserEntityByIdSuccessful() {
        when(userRepository.findById(existedUserDto.getId())).thenReturn(Optional.of(existedUserEntity));

        assertDoesNotThrow(() -> userService.getUserEntityById(existedUserDto.getId()));
    }

    @Test
    void testGetUserEntityByIdFailed() {
        when(userRepository.findById(existedUserDto.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getUserEntityById(existedUserDto.getId()));
    }
}

