package com.example.project_dto.responsitory;

import com.example.project_dto.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
