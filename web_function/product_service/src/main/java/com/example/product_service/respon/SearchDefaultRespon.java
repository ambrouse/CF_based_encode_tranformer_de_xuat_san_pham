package com.example.product_service.respon;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchDefaultRespon {
    List<CustomType> _list_type_product;
    List<CustomType> _list_brand_product;
    List<CustomType> _list_material_product;
    List<CustomType> _list_seasion_product;
    List<CustomType> _list_style_product;
}
