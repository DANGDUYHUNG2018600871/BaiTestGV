package com.example.project_dto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//// Bảng Cấu Hình Lương
public class SalaryConfigurations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double basicSalaryRate;
    private Double overTimeRate;
    private Double allowanceRate;
    private Double taxRate;
    private Double insuranceRate;

    @OneToMany(mappedBy = "salaryConfigurations")
    private List<Salaries> salaries;

}
