package com.example.product_service.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckTokenRespon {
    String _name_user;
    int _id_img_user;
}
