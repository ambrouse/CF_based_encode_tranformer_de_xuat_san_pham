package com.example.card_service.service;

import com.example.card_service.err_manager.CustomException;
import com.example.card_service.repo.PaymentMethodRepo;
import com.example.card_service.respon.PaymentMethodRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    @Autowired
    PaymentMethodRepo paymentMethodRepo;

    public List<PaymentMethodRespon> get_payment_method_service(){

            return paymentMethodRepo.get_payment_method_with_id_user().stream().map(t->{
                return PaymentMethodRespon.builder()
                        ._id((String) t.get("_id"))
                        ._name((String) t.get("_name"))
                        .build();
            }).toList();
    }
}
