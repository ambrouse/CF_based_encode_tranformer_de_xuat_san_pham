package com.example.card_service.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplyCardRequest {
    @NotEmpty(message = "id card is empty")
    String _id_card;
    @NotEmpty(message = "id address is empty")
    String _id_address;
    @NotEmpty(message = "id payment method is empty")
    String _id_payment_method;
    @NotEmpty(message = "phone method is empty")
    @Size(min = 11,max = 11, message = "Phone wrong format")
    String _phone_number;
}
