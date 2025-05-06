package com.example.user_service.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressRequest {
    @NotEmpty(message = "id address is empty")
    String _id;
    @NotEmpty(message = "name address is empty")
    String _name;
}
