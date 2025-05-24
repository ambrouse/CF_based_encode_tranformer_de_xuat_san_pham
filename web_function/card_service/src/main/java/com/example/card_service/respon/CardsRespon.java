package com.example.card_service.respon;

import com.example.card_service.entity.PaymentMethodEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardsRespon {
    CardRespon _cards_respon;
    List<AddressRespon> _address_respon;
    List<PaymentMethodRespon> _payment_method_respon;
}
