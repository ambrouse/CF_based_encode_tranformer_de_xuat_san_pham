package com.example.card_service.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCardRequest {
    @NotEmpty(message = "_id_manager_size_color is empty")
    String _id_manager_size_color;
    @Min(value = 1,message = "price is small")
    int _price;
}
