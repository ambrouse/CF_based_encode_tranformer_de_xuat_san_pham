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
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    DecodeToken decodeToken;
    @Autowired
    UserRepo userRepo;

    public ApiRespon<UserRespon> get_user_service(HttpServletRequest _request){
        String _id_user = decodeToken.getTokenFromCookies(_request);
        List<Tuple> tuples_user = userRepo.get_user_with_id(_id_user);
        List<AddressRespon> _address_respon =  tuples_user.stream().map(t->{
            return AddressRespon.builder()
                    ._id((String) t.get("_id_address"))
                    ._name((String) t.get("_name_address"))
                    .build();
        }).toList();
        UserRespon _user_respon = UserRespon.builder()
                ._name_user((String) tuples_user.get(0).get("_name_user"))
                ._age_user((byte) tuples_user.get(0).get("_age_user"))
                ._time_of_birth_user(tuples_user.get(0).get("_time_of_birth_user")==null? null:((Timestamp) tuples_user.get(0).get("_time_of_birth_user")).toLocalDateTime())
                ._img_user((byte[]) tuples_user.get(0).get("_img_user"))
                ._address_users_respon(_address_respon)
                ._gmail((String) tuples_user.get(0).get("_gmail"))
                ._phone_number((String) tuples_user.get(0).get("_phone_number"))
                ._job_user((String) tuples_user.get(0).get("_job_user"))
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
            _user_entity.set_age(_user_entity.get_age());
            _user_entity.set_job(_user_request.get_job_user());
            _user_entity.set_time_of_birth(_user_request.get_time_of_birth_user());
            _user_entity.set_phone_number(_user_request.get_phone_number());
            userRepo.save(_user_entity);
            return ApiRespon.<UpdateRespon>builder()
                    ._request(200)
                    ._request_desription("Update user api")
                    ._result(UpdateRespon.builder()
                            ._status("Update submit")
                            .build())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(CustomException.builder()
                    .statusCode(400)
                    .message("User update err: \n"+e)
                    .build());
        }
    }

}
