package com.example.card_service.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRespon {
    String _id;
    byte _status_card;
    int _total_price;
    LocalDateTime _day_card;
    LocalDateTime _day_update_status;
    String _phone_number;
    int _shipping_fee;
    List<CardMiniRespon> _cards_desription;
}
