package com.example.product_service.respon;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeatureRespon {
    String _id_manager_size_color;
    String _name_user;
    short _age;
    short _month;
    String _job;
    boolean _sex;
    int _total_price_buy_product;
    int _count_click;
    String _search_value;
    int _count_pay_product;
    int month_pay_product;
    int _total_count_product;
    String _name_product_last_buy;
    int _last_purchase;
    int _cancel;
    int _return;
    int _count_not_pay;
    short _rate;
    String _coment_product;
    int _discount;
    String _name_product;
    String _type;
    String _seasion;
    String _color;
    String _brand;
    String _style;
    String _size;
    int _sales;
    int _price;
    String _description;
}
