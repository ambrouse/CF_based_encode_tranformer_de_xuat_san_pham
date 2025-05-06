package com.example.user_service.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRespon {
    String _name_user;
    byte _age_user;
    LocalDateTime _time_of_birth_user;
    byte[] _img_user;
    List<AddressRespon> _address_users_respon;
    String _gmail;
    String _phone_number;
    String _job_user;
}
