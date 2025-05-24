package com.example.auth_service.service;

import com.example.auth_service.api.ApiRespon;
import com.example.auth_service.err_manager.CustomException;
import com.example.auth_service.repo.AuthRepo;
import com.example.auth_service.repo.UserRepo;
import com.example.auth_service.request.LoginRequest;
import com.example.auth_service.respon.LoginRespon;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    AuthRepo authRepo;
    @Autowired
    SignUpService signUpService;

    public ApiRespon<LoginRespon> login_service(LoginRequest loginRequest, HttpServletResponse _response){

        if(userRepo.get_user_with_gmail(loginRequest.get_email())==null){
            throw new RuntimeException(CustomException.builder()
                    .message("Gmail is not exist")
                    .build());
        }

        Tuple _auth = authRepo.check_login(loginRequest.get_email(),loginRequest.get_password());

        if(_auth == null){
            throw new RuntimeException(CustomException.builder()
                    .message("Password is error")
                    .build());
        }

        String _id_user = userRepo.get_user_with_id_auth(_auth.get(0).toString());
        String _token = signUpService.generate_token(_id_user,_auth.get(1).toString());
        ResponseCookie cookie = ResponseCookie.from("access_token", _token)
                .httpOnly(true)
                .secure(false) // chỉ dùng nếu HTTPS
                .sameSite("Strict") // hoặc "Lax"
                .path("/")
                .maxAge(Duration.ofDays(7))
                .build();

        _response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ApiRespon.<LoginRespon>builder()
                ._request(200)
                ._request_desription("sigh in api")
                ._result(LoginRespon.builder()
                        ._status("Login submit")
                        .build())
                .build();
    }

}
