package com.example.project_dto.responsitory;

import com.example.project_dto.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserResponsitory extends JpaRepository<User,Long> {
    //DSL
    boolean existsByUsername(String username);
    Optional<User> getUsersByUsername(String username);
}
