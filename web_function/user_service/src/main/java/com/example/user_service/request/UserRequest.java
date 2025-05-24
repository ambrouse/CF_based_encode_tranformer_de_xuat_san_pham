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
    @NotEmpty(message = "Tên người dùng không được để trống")
    String _name_user;

    @Min(value = 3, message = "Tuổi quá nhỏ (tối thiểu là 3)")
    @Max(value = 100, message = "Tuổi vượt quá giới hạn cho phép (tối đa là 100)")
    byte _age_user;

    @PastOrPresent(message = "Thời gian sinh phải là trong quá khứ hoặc hiện tại")
    LocalDateTime _time_of_birth_user;

    @Size(max = 10, min = 10, message = "Số điện thoại không đúng định dạng (phải gồm 10 chữ số)")
    String _phone_number;

    @NotEmpty(message = "Nghề nghiệp không được để trống")
    String _job_user;
}
