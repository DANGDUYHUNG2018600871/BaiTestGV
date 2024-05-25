package com.example.project_dto.dto.request;

import com.example.project_dto.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class UserCreateRequest {
    @Size(min = 3,message = "INVALID_USERNAME")
    String username;
    @Size(min = 4,message = "INVALID_PASSWORD")
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
    @Transient
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
//    @Transient
//    public String getPhotosImagePath() {
//        if(this.photo == null || this.id == null) {
//            return "/images/img.png";
//        }
//        return "/upload-photos/user-photos/" + this.id + "/" + this.photo;
//    }
}
