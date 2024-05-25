package com.example.project_dto.controller;

import com.example.project_dto.dto.request.UserCreateRequest;
import com.example.project_dto.dto.request.UserUpdateRequest;
import com.example.project_dto.dto.response.ApiResponse;
import com.example.project_dto.dto.response.UserResponse;
import com.example.project_dto.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {

    UserService userService;
    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUserApi(request))
                .code(200)
                .build();

    }
    @GetMapping

    public ApiResponse<Page<UserResponse>> getAllUser(@RequestParam int page,
                                                      @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserResponse> userResponses = userService.getAllUserApi(pageable);
        return ApiResponse.<Page<UserResponse>>builder()
                .result(userResponses)
                .code(200)
                .build();
    }


    @GetMapping("{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable("id") Long userId){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserByIdApi(userId))
                .code(200)
                .build();
    }

    @PutMapping("{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable("id") Long userId,@RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUserApi(userId,request))
                .code(200)
                .build();
    }
    @DeleteMapping("{id}")
    public ApiResponse<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return ApiResponse.<String>builder()
                .result("oki")
                .code(200)
                .build();
    }

}
