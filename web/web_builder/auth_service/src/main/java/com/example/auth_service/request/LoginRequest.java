package com.example.auth_service.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotEmpty(message = "Email is not empty")
    @Email(message = "Email wrong format")
    String _email;

    @Size(min = 8,message = "Password is short")
    @NotEmpty(message = "Password is not empty")
    String _password;
}
