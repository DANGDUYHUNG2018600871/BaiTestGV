package com.example.project_dto.service;

import com.example.project_dto.entity.User;
import com.example.project_dto.exception.AppException;
import com.example.project_dto.exception.ErrException;
import com.example.project_dto.responsitory.UserResponsitory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserResponsitory userResponsitory;

    public CustomUserDetailsService(UserResponsitory userResponsitory) {
        this.userResponsitory = userResponsitory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userResponsitory.getUsersByUsername(username)
                .orElseThrow(() -> new AppException(ErrException.USER_NO_EXISTED));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(role -> role.getName().name()).toArray(String[]::new))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!user.getActive())
                .build();
    }
}
