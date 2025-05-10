package com.example.auth_service.request;


import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {
    @Email(message = "Gmail wrong format")
    @NotEmpty(message = "Gmail is not empty")
    String _gmail;
    @NotEmpty(message = "Name is not empty")
    String _name;
    @Min(value = 3,message = "Age is small")
    @Max(value = 100,message = "Age is small")
    Byte _age;
    @PastOrPresent(message = "time of birth wrong format")
    LocalDateTime _time_of_birth;
    @NotEmpty(message = "Password is not empty")
    String _password;
    @NotEmpty(message = "Phone is not empty")
    String _phone;
}
