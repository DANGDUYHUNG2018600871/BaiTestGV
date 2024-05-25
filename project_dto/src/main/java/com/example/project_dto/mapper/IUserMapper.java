package com.example.project_dto.mapper;

import com.example.project_dto.dto.request.UserCreateRequest;
import com.example.project_dto.dto.request.UserUpdateRequest;
import com.example.project_dto.dto.response.UserResponse;
import com.example.project_dto.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    User toUser(UserCreateRequest request);
    UserResponse toUserReponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
