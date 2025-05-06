package com.example.auth_service.controller;


import com.example.auth_service.api.ApiRespon;
import com.example.auth_service.request.LoginRequest;
import com.example.auth_service.request.SignUpRequest;
import com.example.auth_service.respon.LoginRespon;
import com.example.auth_service.respon.SignUpRespon;
import com.example.auth_service.service.LoginService;
import com.example.auth_service.service.SignUpService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth-service/api/v1")
public class HomeController {
    @Autowired
    SignUpService signUpService;
    @Autowired
    LoginService loginService;

    @PostMapping("/sign-up")
    public ApiRespon<SignUpRespon> sign_up(@RequestBody @Valid SignUpRequest signUpRequest, HttpServletResponse _response){

        return signUpService.signup_service(signUpRequest,_response);
    }

    @PostMapping("/login")
    public ApiRespon<LoginRespon> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse _respon){

        return loginService.login_service(loginRequest,_respon);
    }
}
