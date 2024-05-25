package com.example.project_dto.service;

import com.example.project_dto.dto.request.UserCreateRequest;
import com.example.project_dto.dto.request.UserUpdateRequest;
import com.example.project_dto.dto.response.UserResponse;
import com.example.project_dto.entity.Role;
import com.example.project_dto.entity.User;
import com.example.project_dto.exception.AppException;
import com.example.project_dto.exception.ErrException;
import com.example.project_dto.mapper.IUserMapper;
import com.example.project_dto.responsitory.RoleRepository;
import com.example.project_dto.responsitory.UserResponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService{

    UserResponsitory userResponsitory;
    IUserMapper userMapper;
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    public UserResponse createUserApi(UserCreateRequest request) {
        if(userResponsitory.existsByUsername(request.getUsername()))
            throw new AppException(ErrException.USER_EXISTED);
        User user= userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // Lấy danh sách các vai trò từ request và gán cho user
        List<Role> roles = new ArrayList<>();
        for (Role role : request.getRoles()) {
            Role foundRole = roleRepository.findById(role.getId())
                    .orElseThrow(() -> new AppException(ErrException.ROLE_NO_EXISTED));
            roles.add(foundRole);
        }
        user.setRoles(roles);
        return userMapper.toUserReponse(userResponsitory.save(user));
    }


    public Page<UserResponse> getAllUserApi(Pageable pageable){
        Page<User> usersPage = userResponsitory.findAll(pageable);
        return usersPage.map(userMapper::toUserReponse);
    }

    public UserResponse getUserByIdApiModel(Long userId){
        Optional<User> user =userResponsitory.findById(userId);
        if (user.isPresent()){
            UserResponse userResponse=modelMapper.map(user.get(),UserResponse.class);
            return userResponse;
        }
        else {
            return null;
        }
    }
    public UserResponse getUserByIdApi(Long userId){
        return userMapper.toUserReponse(userResponsitory.findById(userId)
                .orElseThrow(()->new AppException(ErrException.USER_NO_EXISTED)));
    }


    public UserResponse updateUserApi(Long userId, UserUpdateRequest request) {
        User user=userResponsitory.findById(userId)
                .orElseThrow(()->new RuntimeException("User is not exist with given id :"+userId));
        userMapper.updateUser(user,request);
        return userMapper.toUserReponse(userResponsitory.save(user));
    }
    public void deleteUser(Long userId){
        User user=userResponsitory.findById(userId)
                .orElseThrow(()->new RuntimeException("User is not exist with given id :"+userId));
        userResponsitory.deleteById(userId);
    }

}
