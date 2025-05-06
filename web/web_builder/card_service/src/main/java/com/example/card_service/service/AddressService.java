package com.example.card_service.service;


import com.example.card_service.repo.AddressRepo;
import com.example.card_service.respon.AddressRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepo addressRepo;
    @Autowired
    DecodeTokenService decodeTokenService;


    public List<AddressRespon> get_address_service(String _id_user){

            return addressRepo.get_address_with_id_user(_id_user).stream().map(t->{
                return AddressRespon.builder()
                        ._id((String) t.get("_id"))
                        ._name((String) t.get("_name"))
                        .build();
            }).toList();
    }
}
