package com.example.user_service.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddAddressRequest {
    @NotEmpty(message = "Địa chỉ không được trống.")
    String _name;
}
