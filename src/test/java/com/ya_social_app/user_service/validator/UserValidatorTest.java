package com.ya_social_app.user_service.validator;

import com.ya_social_app.user_service.dto.UserDto;
import com.ya_social_app.user_service.exception.DataValidationException;
import com.ya_social_app.user_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {

    @InjectMocks
    private UserValidator userValidator;

    @Mock
    private UserRepository userRepository;

    UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = UserDto.builder()
                .id(null)
                .username("username")
                .email("email@email.com")
                .build();
    }

    @Test
    public void testValidateCreatedUserSuccessful() {
        when(userRepository.existsUserByUsername(userDto.getUsername())).thenReturn(false);
        when(userRepository.existsUserByEmail(userDto.getEmail())).thenReturn(false);

        assertDoesNotThrow(() -> userValidator.validateCreatedUser(userDto));
    }

    @Test
    public void testValidateCreatedUserInvalidId() {
        userDto.setId(1L);

        assertThrows(DataValidationException.class, () -> userValidator.validateCreatedUser(userDto));
    }

    @Test
    public void testValidateCreatedUserInvalidUsername() {
        when(userRepository.existsUserByUsername(userDto.getUsername())).thenReturn(true);

        assertThrows(DataValidationException.class, () -> userValidator.validateCreatedUser(userDto));
    }

    @Test
    public void testValidateCreatedUserInvalidEmail() {
        when(userRepository.existsUserByUsername(userDto.getUsername())).thenReturn(false);
        when(userRepository.existsUserByEmail(userDto.getEmail())).thenReturn(true);

        assertThrows(DataValidationException.class, () -> userValidator.validateCreatedUser(userDto));
    }
}
