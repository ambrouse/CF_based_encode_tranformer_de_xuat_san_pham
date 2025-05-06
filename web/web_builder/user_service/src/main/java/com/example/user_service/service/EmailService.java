package com.example.user_service.service;

import com.example.user_service.api.ApiRespon;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.err_manager.CustomException;
import com.example.user_service.repo.UserRepo;
import com.example.user_service.request.EmailRequest;
import com.example.user_service.respon.UpdateRespon;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    DecodeToken decodeToken;

    public ApiRespon<UpdateRespon> update_email(HttpServletRequest _request, EmailRequest _email_request){
        try {
            String _id_user = decodeToken.getTokenFromCookies(_request);
            UserEntity _user = userRepo.findById(_id_user).get();
            _user.set_email(_email_request.get_email());
            userRepo.save(_user);
            return ApiRespon.<UpdateRespon>builder()
                    ._request(200)
                    ._request_desription("Update email api")
                    ._result(UpdateRespon.builder()
                            ._status("Update email submit")
                            .build())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(CustomException.builder()
                    .statusCode(400)
                    .message("Update email err: \n"+e)
                    .build());
        }

    }
}
