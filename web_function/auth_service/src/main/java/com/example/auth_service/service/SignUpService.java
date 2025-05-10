package com.example.auth_service.service;


import com.example.auth_service.api.ApiRespon;
import com.example.auth_service.entity.AuthEntity;
import com.example.auth_service.entity.FeatureProductEntity;
import com.example.auth_service.entity.ManagerSizeColorEntity;
import com.example.auth_service.entity.UserEntity;
import com.example.auth_service.err_manager.CustomException;
import com.example.auth_service.repo.AuthRepo;
import com.example.auth_service.repo.FeatureProductRepo;
import com.example.auth_service.repo.ManagerSizeColorRepo;
import com.example.auth_service.repo.UserRepo;
import com.example.auth_service.request.SignUpRequest;
import com.example.auth_service.respon.SignUpRespon;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SignUpService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    AuthRepo authRepo;
    @Autowired
    ManagerSizeColorRepo managerSizeColorRepo;
    @Autowired
    FeatureProductRepo featureProductRepo;

    @NonFinal
    private final String SECRET_KEY = "aHa66Jus+2XX03YJO7MFcs6RnN4eHCsjLmmcIATMmjEi7RctICJiZzI3dzWZrh2q";

    public ApiRespon<SignUpRespon> signup_service(SignUpRequest signUpRequest, HttpServletResponse _response){

        if(userRepo.get_user_with_gmail(signUpRequest.get_gmail())!=null){
            throw new RuntimeException(CustomException.builder()
                    .message("Gmail is exist")
                    .build());
        }
        if(userRepo.get_user_with_phone(signUpRequest.get_phone())!=null){
            throw new RuntimeException(CustomException.builder()
                    .message("Phone number is exist")
                    .build());
        }

        AuthEntity authEntity = AuthEntity.builder()
                ._name(signUpRequest.get_gmail())
                ._password(signUpRequest.get_password())
                ._status_account("1")
                ._id_role("2")
                .build();
        AuthEntity authEntity1 = authRepo.save(authEntity);

        UserEntity userEntity = UserEntity.builder()
                ._age(signUpRequest.get_age())
                ._email(signUpRequest.get_gmail())
                ._id_authen(authEntity1.get_id())
                ._name(signUpRequest.get_name())
                ._phone_number(signUpRequest.get_phone())
                ._time_of_birth(signUpRequest.get_time_of_birth())
                .build();

        UserEntity userEntity1 =  userRepo.save(userEntity);

        String _token = generate_token(userEntity1.get_id(),"USER");

        List<ManagerSizeColorEntity> managerSizeColorEntities = managerSizeColorRepo.findAll().stream().toList();

        for(ManagerSizeColorEntity i : managerSizeColorEntities) {
           FeatureProductEntity featureProductEntity = FeatureProductEntity.builder()
                   .idManagerSizeColor(i.getId())
                   .idUser(userEntity1.get_id())
                   .build();
           featureProductRepo.save(featureProductEntity);
        }

        ResponseCookie cookie = ResponseCookie.from("access_token", _token)
                .httpOnly(true)
                .secure(false) // chỉ dùng nếu HTTPS
                .sameSite("Strict") // hoặc "Lax"
                .path("/")
                .maxAge(Duration.ofDays(7))
                .build();

        _response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ApiRespon.<SignUpRespon>builder()
                ._request(200)
                ._request_desription("sign up api")
                ._result(SignUpRespon.builder()
                        ._status("Sign-up submit")
                        .build())
                .build();
    }

    public String generate_token(String username, String _name_role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", List.of(_name_role))
                .setIssuedAt(new Date())
                .setExpiration(new Date(Instant.now().plus(7, ChronoUnit.DAYS).toEpochMilli()))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

}
