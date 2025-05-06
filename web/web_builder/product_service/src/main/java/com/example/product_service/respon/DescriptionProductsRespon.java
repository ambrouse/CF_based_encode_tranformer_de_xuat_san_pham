package com.example.product_service.respon;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DescriptionProductsRespon {
    String _id_manager_size_color;
    String _id_size;
    String _id_color;
    String _name_color;
    String _name_size;
    int _sales;
    int _inventory;
    int _rate;
    List<String> _list_img;
    List<ComentRespon> _list_coment;
}
