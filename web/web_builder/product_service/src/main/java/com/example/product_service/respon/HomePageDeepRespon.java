package com.example.product_service.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomePageDeepRespon {
    List<FeatureRespon> _features_respon;
}
