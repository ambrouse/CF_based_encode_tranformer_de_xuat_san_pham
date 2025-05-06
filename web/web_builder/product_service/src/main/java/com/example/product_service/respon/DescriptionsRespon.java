package com.example.product_service.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DescriptionsRespon {
    DescriptionProductRespon _description_product_respons;
    List<DescriptionProductsRespon> description_products_respons;
}
