package com.example.product_service.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddImgRequest {
    byte[] _img;
    String _id_manager_size_color;
}
