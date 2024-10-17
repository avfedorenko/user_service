package com.ya_social_app.user_service.mapper;

import com.ya_social_app.user_service.dto.UserDto;
import com.ya_social_app.user_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> userList);

    List<User> toEntityList(List<UserDto> userDtoList);

}
