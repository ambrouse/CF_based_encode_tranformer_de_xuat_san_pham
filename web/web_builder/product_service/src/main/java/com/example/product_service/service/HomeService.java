package com.example.product_service.service;

import com.example.product_service.api.ApiRespon;
import com.example.product_service.entity.UserEntity;
import com.example.product_service.err_manager.CustomException;
import com.example.product_service.repo.ProductRepo;
import com.example.product_service.repo.UserRepo;
import com.example.product_service.respon.CheckTokenRespon;
import com.example.product_service.respon.DeepRespon;
import com.example.product_service.respon.HomeRespon;
import com.example.product_service.respon.HomesRespon;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    FeatureService featureService;
    @Autowired
    DecodeToken decodeToken;
    @Autowired
    UserRepo userRepo;

    public ApiRespon<HomesRespon> home_service(HttpServletRequest _request){
        List<Tuple> _tuples = productRepo.home();

        List<HomeRespon> homeRespons = _tuples.stream().map(_t -> {
            HomeRespon _res = new HomeRespon();
            _res.set_id_product((String) _t.get("_id_product"));
            _res.set_name_product((String) _t.get("_name_product"));
            _res.set_desription_product((String) _t.get("_desription_product"));
            _res.set_type_product((String) _t.get("_type_product"));
            _res.set_seasion_product((String) _t.get("_seasion_product"));
            _res.set_style_product((String) _t.get("_style_product"));
            _res.set_rate(_t.get("_rate_product") != null ? ((Number) _t.get("_rate_product")).shortValue() : 0);
            _res.set_img_product((String) _t.get("_img_product"));
            _res.set_price_product(_t.get("_price_product")==null? 0:(int) _t.get("_price_product"));
            return _res;
        }).toList();
        try {
            List<DeepRespon> _deep_respon = featureService.product_recommendation_service(_request);
            if(_deep_respon!=null){
                List<HomeRespon> home_respon_recoment = productRepo.home_recoment(_deep_respon.stream().map(DeepRespon::get_id).collect(Collectors.toList())).stream().map(_t -> {
                    HomeRespon _res = new HomeRespon();
                    _res.set_id_product((String) _t.get("_id_product"));
                    _res.set_name_product((String) _t.get("_name_product"));
                    _res.set_desription_product((String) _t.get("_desription_product"));
                    _res.set_type_product((String) _t.get("_type_product"));
                    _res.set_seasion_product((String) _t.get("_seasion_product"));
                    _res.set_style_product((String) _t.get("_style_product"));
                    _res.set_rate(_t.get("_rate_product") != null ? ((Number) _t.get("_rate_product")).shortValue() : 0);
                    _res.set_img_product((String) _t.get("_img_product"));
                    _res.set_price_product(_t.get("_price_product")==null? 0:(int) _t.get("_price_product"));
                    return _res;
                }).toList();
                return ApiRespon.<HomesRespon>builder()
                        ._request(200)
                        ._request_desription("home api")
                        ._result(HomesRespon.builder()
                                ._homes_respon(homeRespons)
                                ._recoment_products_respon(home_respon_recoment)
                                .build())
                        .build();


            }
            return ApiRespon.<HomesRespon>builder()
                        ._request(200)
                        ._request_desription("home api")
                        ._result(HomesRespon.builder()
                                ._homes_respon(homeRespons)
                                ._recoment_products_respon(null)
                                .build())
                        .build();
        } catch (RuntimeException e) {
            return ApiRespon.<HomesRespon>builder()
                    ._request(200)
                    ._request_desription("home api")
                    ._result(HomesRespon.builder()
                            ._homes_respon(homeRespons)
                            ._recoment_products_respon(null)
                            .build())
                    .build();
        }

    }

        public ApiRespon<HomesRespon> get_recoment_service(HttpServletRequest _request){
            try{
                List<DeepRespon> _deep_respon = featureService.product_recommendation_service(_request);
                if(_deep_respon==null){
                    throw new RuntimeException(CustomException.builder()
                            .statusCode(401)
                            .message("No login")
                            .build());
                }

                List<HomeRespon> home_respon_recoment = productRepo.home_recoment(_deep_respon.stream().map(DeepRespon::get_id).collect(Collectors.toList())).stream().map(_t -> {
                    HomeRespon _res = new HomeRespon();
                    _res.set_id_product((String) _t.get("_id_product"));
                    _res.set_name_product((String) _t.get("_name_product"));
                    _res.set_desription_product((String) _t.get("_desription_product"));
                    _res.set_type_product((String) _t.get("_type_product"));
                    _res.set_seasion_product((String) _t.get("_seasion_product"));
                    _res.set_style_product((String) _t.get("_style_product"));
                    _res.set_rate(_t.get("_rate_product") != null ? ((Number) _t.get("_rate_product")).shortValue() : 0);
                    _res.set_img_product((String) _t.get("_img_product"));
                    _res.set_price_product(_t.get("_price_product")==null? 0:(int) _t.get("_price_product"));
                    return _res;
                }).toList();
                return ApiRespon.<HomesRespon>builder()
                        ._request(200)
                        ._request_desription("home api")
                        ._result(HomesRespon.builder()
                                ._homes_respon(null)
                                ._recoment_products_respon(home_respon_recoment)
                                .build())
                        .build();
            } catch (RuntimeException e) {
                throw new RuntimeException(CustomException.builder()
                        .message(e.toString())
                        .statusCode(400)
                        .build());
            }

        }

        public ApiRespon<CheckTokenRespon> check_token(HttpServletRequest httpServletRequest){
            String _id_user = decodeToken.getTokenFromCookies(httpServletRequest);
            Tuple tuple = userRepo.get_user_width_id(_id_user);
            if(_id_user == null){
                throw new RuntimeException(CustomException.builder()
                        .statusCode(401)
                        .message("No login")
                        .build());
            }
            return ApiRespon.<CheckTokenRespon>builder()
                    ._request(200)
                    ._request_desription("check token api")
                    ._result(CheckTokenRespon.builder()
                            ._name_user((String)tuple.get("_name"))
                            ._id_img_user((int)tuple.get("_id_img_user"))
                            .build())
                    .build();
        }

        public ResponseEntity<byte[]> get_img(String _id_user){
            UserEntity userEntity = userRepo.findById(_id_user).get();
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(userEntity.get_img_user());
        }
}
