package com.example.user_service.service;

import com.example.user_service.api.ApiRespon;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.err_manager.CustomException;
import com.example.user_service.repo.UserRepo;
import com.example.user_service.request.UserRequest;
import com.example.user_service.respon.AddressRespon;
import com.example.user_service.respon.UpdateRespon;
import com.example.user_service.respon.UserRespon;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    DecodeToken decodeToken;
    @Autowired
    UserRepo userRepo;

    public ApiRespon<UserRespon> get_user_service(HttpServletRequest _request){
        String _id_user = decodeToken.getTokenFromCookies(_request);
        System.out.println(_id_user);
        UserEntity _user_entity = userRepo.findById(_id_user).get();

        List<Tuple> tuples =  userRepo.get_all_address(_id_user);
        List<AddressRespon> _address_respon = new ArrayList<>();
        if(!tuples.isEmpty()){
            _address_respon =  tuples.stream().map(t->{
                return AddressRespon.builder()
                        ._id((String) t.get("_id"))
                        ._name((String) t.get("_name"))
                        .build();
            }).toList();
        }
        UserRespon _user_respon = UserRespon.builder()
                ._name_user(_user_entity.get_name())
                ._age_user(_user_entity.get_age())
                ._time_of_birth_user(_user_entity.get_time_of_birth())
                ._id_user(_id_user)
                ._address_users_respon(_address_respon.isEmpty()?null:_address_respon)
                ._gmail(_user_entity.get_email())
                ._phone_number(_user_entity.get_phone_number())
                ._job_user(_user_entity.get_job())
                .build();
        return ApiRespon.<UserRespon>builder()
                ._request(200)
                ._request_desription("get user respon")
                ._result(_user_respon)
                .build();
    }

    public ApiRespon<UpdateRespon> update_user_service(HttpServletRequest _request, UserRequest _user_request){
        try {
            String _id_user = decodeToken.getTokenFromCookies(_request);
            UserEntity _user_entity = userRepo.findById(_id_user).get();
            _user_entity.set_name(_user_request.get_name_user());
            _user_entity.set_age(_user_request.get_age_user());
            _user_entity.set_job(_user_request.get_job_user());
            _user_entity.set_time_of_birth(_user_request.get_time_of_birth_user());
            _user_entity.set_phone_number(_user_request.get_phone_number());
            userRepo.save(_user_entity);
            return ApiRespon.<UpdateRespon>builder()
                    ._request(200)
                    ._request_desription("Update user api")
                    ._result(UpdateRespon.builder()
                            ._status("Đã cập nhật thông tin.")
                            .build())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(CustomException.builder()
                    .statusCode(400)
                    .message("User update err: \n"+e)
                    .build());
        }
    }

    public ResponseEntity<byte[]> get_img(String id){
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(userRepo.findById(id).get().get_img_user());
    }

}
