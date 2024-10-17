package com.ya_social_app.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank(message = "Username can't be empty")
    @Size(max = 64)
    private String username;

    @NotBlank(message = "E-mail can't be empty")
    @Size(max = 64)
    @Email
    private String email;

    @Size(max = 4096)
    private String about;

}
