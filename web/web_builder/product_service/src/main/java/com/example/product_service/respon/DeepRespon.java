package com.example.product_service.respon;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeepRespon {
    String _id;
    String _predict;
}
