package com.example.card_service.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardMiniRespon {
    String _id_card_mini;
    String _id_manager_size_color;
    String _id_size;
    String _id_color;
    String _name_product;
    String _name_size;
    String _name_color;
    int _count;
    int _price;
    String _img_product;
    String _id_product;
    List<ManagerSizeColorRespon> _manager_size_colors_respon;
}
