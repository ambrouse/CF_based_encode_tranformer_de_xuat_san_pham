package com.example.product_service.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddComentRequest {
    String _id_manager_size_color;
    String _content;
    byte[] _imgs_coment;
}
