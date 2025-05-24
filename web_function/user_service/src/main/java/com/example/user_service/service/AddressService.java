package com.example.user_service.service;

import com.example.user_service.api.ApiRespon;
import com.example.user_service.entity.AddressEntity;
import com.example.user_service.err_manager.CustomException;
import com.example.user_service.repo.AddressRepo;
import com.example.user_service.request.AddressRequest;
import com.example.user_service.respon.UpdateRespon;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddressService {

    @Autowired
    AddressRepo addressRepo;
    @Autowired
    DecodeToken decodeToken;

    public ApiRespon<UpdateRespon> update_address_service(AddressRequest _address_request){

        try {
            AddressEntity _address_enitty =  addressRepo.findById(_address_request.get_id()).get();
            _address_enitty.set_name(_address_request.get_name());
            addressRepo.save(_address_enitty);
            return ApiRespon.<UpdateRespon>builder()
                    ._request(200)
                    ._request_desription("Update address api")
                    ._result(UpdateRespon.builder()
                            ._status("Đã cập nhật địa chỉ.")
                            .build())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(CustomException.builder()
                    .statusCode(400)
                    .message("Update address err: \n"+e)
                    .build());
        }
    }

    public ApiRespon<UpdateRespon> delete_address_service(String _id_address){

        try {
            AddressEntity _address_enitty =  addressRepo.findById(_id_address).get();
            _address_enitty.set_day_delete_tabel(LocalDateTime.now());
            addressRepo.save(_address_enitty);
            return ApiRespon.<UpdateRespon>builder()
                    ._request(200)
                    ._request_desription("Delete address api")
                    ._result(UpdateRespon.builder()
                            ._status("Đã xóa địa chỉ.")
                            .build())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(CustomException.builder()
                    .statusCode(400)
                    .message("Delete address err: \n"+e)
                    .build());
        }
    }

    public ApiRespon<UpdateRespon> add_respon_service(HttpServletRequest _request,AddressRequest _add_address_request){
        try {
            String _id_user = decodeToken.getTokenFromCookies(_request);
            AddressEntity _address_entity = AddressEntity.builder()
                    ._status(true)
                    ._id_user(_id_user)
                    ._name(_add_address_request.get_name())
                    .build();
            addressRepo.save(_address_entity);
            return ApiRespon.<UpdateRespon>builder()
                    ._request(200)
                    ._request_desription("Add address api")
                    ._result(UpdateRespon.builder()
                            ._status("Đã thêm địa chỉ.")
                            .build())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(CustomException.builder()
                    .statusCode(400)
                    .message("Add address err: \n"+e)
                    .build());
        }
    }
}
