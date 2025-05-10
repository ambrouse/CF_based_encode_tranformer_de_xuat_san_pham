package com.example.user_service.request;

import com.example.user_service.respon.AddressRespon;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotEmpty(message = "Name user is empty")
    String _name_user;
    @Min(value = 3,message = "Age is small")
    @Max(value = 100,message = "Age is out")
    byte _age_user;
    @Future
    LocalDateTime _time_of_birth_user;
    @Size(max = 11,min = 11,message = "Phone number is wrong format")
    String _phone_number;
    @NotEmpty(message = "Job is empty")
    String _job_user;
}
