package com.example.project_dto.service;

import com.example.project_dto.entity.Role;
import com.example.project_dto.responsitory.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }
}
