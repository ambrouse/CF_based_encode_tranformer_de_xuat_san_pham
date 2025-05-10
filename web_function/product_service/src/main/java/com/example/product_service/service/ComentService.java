package com.example.product_service.service;


import com.example.product_service.api.ApiRespon;
import com.example.product_service.entity.ComentEntity;
import com.example.product_service.entity.ImgComentEntity;
import com.example.product_service.err_manager.CustomException;
import com.example.product_service.repo.ComentRepo;
import com.example.product_service.repo.ImgComentRepo;
import com.example.product_service.request.AddComentRequest;
import com.example.product_service.respon.ComentRespon;
import com.example.product_service.respon.ComentsRespon;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComentService {

    @Autowired
    ComentRepo comentRepo;
    @Autowired
    ImgComentRepo imgComentRepo;
    @Autowired
    DecodeToken decodeToken;

    public ApiRespon<ComentsRespon> add_coment_service(AddComentRequest add_coment_request, HttpServletRequest _request){
        String _id_user = decodeToken.getTokenFromCookies(_request);


        if(add_coment_request.get_imgs_coment()==null && add_coment_request.get_content()==null){
            throw new RuntimeException(CustomException.builder()
                    .statusCode(400)
                    .message("Not text and img")
                    .build());
        }

        ComentEntity _coment_entity = ComentEntity.builder()
                ._content(add_coment_request.get_content())
                ._id_user(_id_user)
                ._id_manager_size_color(add_coment_request.get_id_manager_size_color())
                ._day_coment(LocalDateTime.now())
                .build();

        ComentEntity _coment_entity1 = comentRepo.save(_coment_entity);

        if(add_coment_request.get_imgs_coment()!=null){
            imgComentRepo.save(ImgComentEntity.builder()
                    ._id_coment(_coment_entity1.get_id())
                    ._img_byte(add_coment_request.get_imgs_coment())
                    .build());
        }

        List<ComentRespon> _coments_respon = comentRepo.get_coment(add_coment_request.get_id_manager_size_color()).stream().map(_t->{
            return ComentRespon.builder()
                    ._id((String) _t.get("_id"))
                    ._name_user((String) _t.get("_name_user"))
                    ._coment((String) _t.get("_coment"))
                    ._img_user((String) _t.get("_img_user"))
                    ._check_user(_t.get("_id_user").equals(_id_user)?true:false)
                    ._id_img_coments(imgComentRepo.get_img_coment(_t.get("_id").toString()))
                    .build();
        }).toList();

        return ApiRespon.<ComentsRespon>builder()
                ._request(200)
                ._request_desription("add coment desription product api")
                ._result(ComentsRespon.builder()
                        .coments_respon(_coments_respon)
                        .build())
                .build();

    }

    public ApiRespon<ComentsRespon> delete_coment_service(String _id,HttpServletRequest _request){
        ComentEntity comentEntity = comentRepo.findById(_id).get();
        comentEntity.set_day_delete_table(LocalDateTime.now());
        String _id_user = decodeToken.getTokenFromCookies(_request);

        comentRepo.save(comentEntity);
        List<ComentRespon> _coments_respon = comentRepo.get_coment(comentEntity.get_id_manager_size_color()).stream().map(_t->{
            return ComentRespon.builder()
                    ._id((String) _t.get("_id"))
                    ._name_user((String) _t.get("_name_user"))
                    ._coment((String) _t.get("_coment"))
                    ._img_user((String) _t.get("_img_user"))
                    ._check_user(_t.get("_id_user").equals(_id_user)?true:false)
                    ._id_img_coments(imgComentRepo.get_img_coment(_t.get("_id").toString()))
                    .build();
        }).toList();

        return ApiRespon.<ComentsRespon>builder()
                ._request(200)
                ._request_desription("delete coment api")
                ._result(ComentsRespon.builder()
                        .coments_respon(_coments_respon)
                        .build())
                .build();
    }


    public ResponseEntity<byte[]> get_img(String id){
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imgComentRepo.findById(id).get().get_img_byte());
    }
}
