package com.example.project_dto.controller;

import com.example.project_dto.dto.request.LoginRequest;
import com.example.project_dto.dto.response.ApiResponse;
import com.example.project_dto.dto.response.AuthResponse;
import com.example.project_dto.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthController {
    AuthService authService;
    @PostMapping("token")
    public ApiResponse<AuthResponse> createToken(@RequestBody LoginRequest authenticationRequest){
        return ApiResponse.<AuthResponse>builder()
                .result(authService.authenticationReponse(authenticationRequest))
                .code(200)
                .build();
    }
}
