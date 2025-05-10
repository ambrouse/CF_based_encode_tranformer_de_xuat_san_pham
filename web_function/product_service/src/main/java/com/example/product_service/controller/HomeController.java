package com.example.product_service.controller;


import com.example.product_service.api.ApiRespon;
import com.example.product_service.repo.ComentRepo;
import com.example.product_service.request.AddComentRequest;
import com.example.product_service.request.AddImgRequest;
import com.example.product_service.request.UpdateFeatureClickRequest;
import com.example.product_service.respon.*;
import com.example.product_service.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product-service/api/v1")
public class HomeController {

    @Autowired
    SearchService searchService;
    @Autowired
    HomeService homeService;
    @Autowired
    DescriptionProductService descriptionProductService;
    @Autowired
    ComentService comentService;
    @Autowired
    FeatureService featureService;
    @Autowired
    AddImgService addImgService;

    @GetMapping("/search")
    public ApiRespon<SearchsRespon> search(@RequestParam(required = false) String _name_product,
                                           @RequestParam(required = false) String _type_product,
                                           @RequestParam(required = false) String _brand_product,
                                           @RequestParam(required = false) String _seasion_product,
                                           @RequestParam(required = false) String _style_product,
                                           @RequestParam(required = false) String _material_product,
                                           @RequestParam(required = false) String _sort_id,
                                           HttpServletRequest _request){

        return searchService.search_service(_name_product,
                                            _type_product,
                                            _brand_product,
                                            _seasion_product,
                                            _style_product,
                                            _material_product,
                                            _sort_id,
                                            _request);
    }

    @GetMapping("/search-default")
    public ApiRespon<SearchDefaultRespon> search_default(){

        return searchService.search_default_service();
    }

    @GetMapping("/home-page")
    public ApiRespon<HomesRespon> home_page(HttpServletRequest _request){

        return homeService.home_service(_request);
    }

    @RequestMapping("/desription-product/{_id}")
    public ApiRespon<DescriptionsRespon> desription_product(@PathVariable String _id,HttpServletRequest _request){

        return descriptionProductService.description_product_service(_id,_request);
    }

    @PutMapping("/desription-product-feature")
    public ApiRespon<UpdateFeatureRespon> desription_product_feature(@RequestBody UpdateFeatureClickRequest update_feature_click_reuest, HttpServletRequest _request){

        return descriptionProductService.update_feature_click_service(_request,update_feature_click_reuest);
    }

    @PostMapping("/coment")
    public ApiRespon<ComentsRespon> add_coment(@RequestBody AddComentRequest add_coment_request,HttpServletRequest _request){

        return comentService.add_coment_service(add_coment_request,_request);
    }

    @DeleteMapping("/coment/{_id}")
    public ApiRespon<ComentsRespon> delete_coment(@PathVariable(name = "_id") String _id,HttpServletRequest _request){

        return comentService.delete_coment_service(_id,_request);
    }

    @PostMapping("/add-img")
    public ApiRespon<String> add_img(@RequestBody AddImgRequest addImgRequest){
        return addImgService.add_img(addImgRequest);
    }

    @RequestMapping(value = "/get-img/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> add_img(@PathVariable(name = "id") String id){
        return addImgService.get_img(id);
    }

    @RequestMapping(value = "/get-img-coment/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> add_img_coment(@PathVariable(name = "id") String id){
        return comentService.get_img(id);
    }


    @RequestMapping(value = "/check_token")
    public ApiRespon<CheckTokenRespon> check_token(HttpServletRequest httpServletRequest){
        return homeService.check_token(httpServletRequest);
    }


}
