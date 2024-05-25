package com.example.project_dto.dto.response;

import com.example.project_dto.entity.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String username;
    String password;
    String firstName;
    String lastName;
    @Email(regexp = "^.+@.+\\..+$", message = "INVALID_USERNAME")
    String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth;
    private Boolean gender;
    @Column(length = 256)
    private String address;

    @Pattern(regexp = "^\\d{10,11}$", message = "INVALID_USERNAME")
    private String phone;

    private String photo;

    private Boolean active;

    private List<Role> roles = new ArrayList<>();
}
