package com.example.user_service.controller;


import com.example.user_service.api.ApiRespon;
import com.example.user_service.request.AddressRequest;
import com.example.user_service.request.EmailRequest;
import com.example.user_service.request.ImgUserRequest;
import com.example.user_service.request.UserRequest;
import com.example.user_service.respon.UpdateRespon;
import com.example.user_service.respon.UserRespon;
import com.example.user_service.service.AddressService;
import com.example.user_service.service.EmailService;
import com.example.user_service.service.ImgUserService;
import com.example.user_service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service/api/v1")
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;
    @Autowired
    EmailService emailService;
    @Autowired
    ImgUserService imgUserService;

    @GetMapping("/profile")
    public ApiRespon<UserRespon> get_user(HttpServletRequest _request){
        return userService.get_user_service(_request);
    }

    @PutMapping("/profile")
    public ApiRespon<UpdateRespon> update_user(HttpServletRequest _request, @RequestBody @Valid UserRequest _user_request){

        return userService.update_user_service(_request,_user_request);
    }

    @PutMapping("/profile-address")
    public ApiRespon<UpdateRespon> update_address(@RequestBody @Valid AddressRequest _address_request){

        return addressService.update_address_service(_address_request);
    }

    @PostMapping("/profile-address")
    public ApiRespon<UpdateRespon> update_address(HttpServletRequest _request,@RequestBody @Valid AddressRequest _add_address_request){

        return  addressService.add_respon_service(_request,_add_address_request);
    }

    @DeleteMapping("/profile-address/{_id}")
    public ApiRespon<UpdateRespon> delete_address(@PathVariable("_id") String _id){

        return  addressService.delete_address_service(_id);
    }

    @PutMapping("/profile-email")
    public ApiRespon<UpdateRespon> update_email(@RequestBody @Valid EmailRequest _email_request, HttpServletRequest _request){

        return  emailService.update_email(_request,_email_request);
    }

    @PutMapping("/profile-img")
    public ApiRespon<UpdateRespon> update_img(@RequestBody @Valid ImgUserRequest _img_user_request, HttpServletRequest _request){

        return  imgUserService.update_img(_request,_img_user_request);
    }

    @RequestMapping("/get_img/{_id}")
    public ResponseEntity<byte[]> get_img(@PathVariable(name = "_id") String _id){

        return  userService.get_img(_id);
    }

}
