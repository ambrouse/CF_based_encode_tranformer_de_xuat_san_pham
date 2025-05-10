package com.example.product_service.respon;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeRespon {
    String _id_product;
    String _name_product;
    String _desription_product;
    short _rate;
    String _type_product;
    String _seasion_product;
    String _style_product;
    String _img_product;
    int _price_product;
}
