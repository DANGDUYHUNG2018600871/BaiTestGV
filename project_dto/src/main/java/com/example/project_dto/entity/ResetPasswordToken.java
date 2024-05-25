package com.example.project_dto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date expiationTime;

    @Column(insertable = false, updatable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
