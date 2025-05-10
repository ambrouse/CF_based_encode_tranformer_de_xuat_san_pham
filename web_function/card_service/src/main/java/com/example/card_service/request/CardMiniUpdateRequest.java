package com.example.card_service.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardMiniUpdateRequest {

    @NotEmpty(message = "id is empty")
    String _id_card_mini;
    @NotEmpty(message = "size or color is empty")
    String _id_manager_size_color;
    @Min(value = 1 , message = "Count is small")
    int _count;
}
