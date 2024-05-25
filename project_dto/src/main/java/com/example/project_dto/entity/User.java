package com.example.project_dto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private Boolean gender;
    @Column(length = 256)
    private String address;

    private String phone;

    private String photo;

    private Boolean active;

    private String activeCode;

    private LocalDateTime codeExpirationTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ResetPasswordToken> resetPasswordTokens;

    //@Transient: Đánh dấu phương thức này không phải là một phần của trạng thái lâu dài và sẽ không được lưu trữ trong cơ sở dữ liệu.

    @OneToMany(mappedBy = "user")
    private List<Salaries> salaries;

    @OneToMany(mappedBy = "user")
    private List<Leaves> leaves;

    @OneToMany(mappedBy = "user")
    private List<WorkSchedules> workSchedules;

    @OneToMany(mappedBy = "user")
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "user")
    private List<UserDepartments> userDepartments;

    @OneToMany(mappedBy = "user")
    private List<Contracts> contracts;

    @OneToMany(mappedBy = "user")
    private List<UserTraining> userTrainings;
    @Transient
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
