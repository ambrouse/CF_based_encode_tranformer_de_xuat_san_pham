package com.example.product_service.service;

import com.example.product_service.api.ApiRespon;
import com.example.product_service.entity.FeatureProductEntity;
import com.example.product_service.repo.*;
import com.example.product_service.request.UpdateFeatureClickRequest;
import com.example.product_service.respon.*;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DescriptionProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ManagerSizeColorRepo managerSizeColorRepo;
    @Autowired
    ImgProductRepo imgProductRepo;
    @Autowired
    ComentRepo comentRepo;
    @Autowired
    ImgComentRepo imgComentRepo;
    @Autowired
    FeatureProductRepo featureProductRepo;
    @Autowired
    DecodeToken decodeToken;


    public ApiRespon<DescriptionsRespon> description_product_service(String _id, HttpServletRequest _request){
        String _id_user = decodeToken.getTokenFromCookies(_request);
        Tuple tuple = productRepo.description_product(_id);
        DescriptionProductRespon descriptionProductRespon = DescriptionProductRespon.builder()
                ._id_product((String) tuple.get("_id_product"))
                ._name_product((String) tuple.get("_name_product"))
                ._desription_product((String) tuple.get("_desription_product"))
                ._type_product((String) tuple.get("_type_product"))
                ._style_product((String) tuple.get("_style_product"))
                ._rate(tuple.get("_rate_product")==null? 0:(short) tuple.get("_rate_product"))
                ._seasion_product((String) tuple.get("_seasion_product"))
                ._price_product(tuple.get("_price_product")==null? 0:(int) tuple.get("_price_product"))
                .build();

        List<Tuple> _tuples = managerSizeColorRepo.get_manager_with_product(_id);

        List<DescriptionProductsRespon> descriptionProductsRespon = _tuples.stream().map(_t -> {
            return DescriptionProductsRespon.builder()
                    ._id_manager_size_color((String) _t.get("_id_manager_size_color"))
                    ._id_size((String) _t.get("_id_size"))
                    ._id_color((String) _t.get("_id_color"))
                    ._name_size((String) _t.get("_name_size"))
                    ._name_color((String) _t.get("_name_color"))
                    ._sales(_t.get("_sales")==null? 0:(int) _t.get("_sales"))
                    ._inventory(_t.get("_inventory")==null? 0:(int) _t.get("_inventory"))
                    ._rate(_t.get("_rate")==null? 0:(short) _t.get("_rate"))
                    .build();
        }).toList();

        for(DescriptionProductsRespon _t : descriptionProductsRespon){
            List<Tuple> _tuples_coment = comentRepo.get_coment(_t.get_id_manager_size_color());
            _t.set_list_img(imgProductRepo.get_all_img_product(_t.get_id_manager_size_color()));
            _t.set_list_coment(_tuples_coment.stream().map(_tu -> {
                System.out.println(_tu.get("_id_user").toString()+"__"+_id_user);
                return ComentRespon.builder()
                        ._id((String) _tu.get("_id"))
                        ._coment((String) _tu.get("_coment"))
                        ._name_user((String) _tu.get("_name_user"))
                        ._img_user((String) _tu.get("_img_user"))
                        ._check_user(_tu.get("_id_user").toString().equals(_id_user)?true:false)
                        ._id_img_coments(imgComentRepo.get_img_coment(_tu.get("_id").toString()))
                        .build();
            }).toList());
        }

        if(_id_user!=null){
            FeatureProductEntity _f = featureProductRepo.update_feature_product_click(_id_user,descriptionProductsRespon.get(0).get_id_manager_size_color());
            _f.set_count_clich(_f.get_count_clich()==null ? 1 : (_f.get_count_clich()+1));
            featureProductRepo.save(_f);
        }

        return ApiRespon.<DescriptionsRespon>builder()
                ._request(200)
                ._request_desription("desription product api")
                ._result(DescriptionsRespon.builder()
                        ._description_product_respons(descriptionProductRespon)
                        .description_products_respons(descriptionProductsRespon)
                        .build())
                .build();
    }


    public ApiRespon<UpdateFeatureRespon> update_feature_click_service(HttpServletRequest _request, UpdateFeatureClickRequest _update_feature_click_request){
        String _id_user = decodeToken.getTokenFromCookies(_request);
        if(_id_user != null){
            FeatureProductEntity _f = featureProductRepo.update_feature_product_click(_id_user,_update_feature_click_request.get_id_manager_size_color());
            _f.set_count_clich(_f.get_count_clich()==null ? 1 : (_f.get_count_clich()+1));
            featureProductRepo.save(_f);
        }
        return ApiRespon.<UpdateFeatureRespon>builder()
                ._request(200)
                ._request_desription("desription product api")
                ._result(UpdateFeatureRespon.builder()
                        ._status("Update feature click submit")
                        .build())
                .build();
    }
}
