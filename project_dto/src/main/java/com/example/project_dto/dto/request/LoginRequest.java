package com.example.project_dto.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @Size(min = 1,message = "INVALID_USERNAME")
    String username;
    @Size(min = 1,message = "INVALID_PASSWORD")
    String password;
}
