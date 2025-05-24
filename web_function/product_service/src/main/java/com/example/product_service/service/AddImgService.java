package com.example.product_service.service;

import com.example.product_service.api.ApiRespon;
import com.example.product_service.entity.ImgProductEntity;
import com.example.product_service.repo.ImgProductRepo;
import com.example.product_service.request.AddImgRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddImgService {

    @Autowired
    ImgProductRepo imgProductRepo;

    public ApiRespon<String> add_img(AddImgRequest addImgRequest){


        ImgProductEntity imgProductEntity = ImgProductEntity.builder()
                .idManagerSizeColor(addImgRequest.get_id_manager_size_color())
                .imgByte(addImgRequest.get_img())
                .build();
        ImgProductEntity imgProductEntity1 = imgProductRepo.save(imgProductEntity);

        return ApiRespon.<String>builder()
                ._request(200)
                ._request_desription("ss")
                ._result(imgProductEntity1.getId())
                .build();

    }

    public ResponseEntity<byte[]> get_img(String id){
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imgProductRepo.findById(id).get().getImgByte());
    }
}
