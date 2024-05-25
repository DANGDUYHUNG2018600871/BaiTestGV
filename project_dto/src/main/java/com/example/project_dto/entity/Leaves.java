package com.example.project_dto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Leaves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean approve;
    private String note;
    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;
}
