package com.example.product_service.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class HomesRespon {
    List<HomeRespon> _homes_respon;
    List<HomeRespon> _recoment_products_respon;
}
