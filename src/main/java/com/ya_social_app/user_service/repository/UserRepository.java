package com.ya_social_app.user_service.repository;

import com.ya_social_app.user_service.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(@NotBlank(message = "Username can't be empty") @Size(max = 64) String username);
}
