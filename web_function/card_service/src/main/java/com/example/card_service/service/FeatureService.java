package com.example.card_service.service;

import com.example.card_service.entity.FeatureProductEntity;
import com.example.card_service.repo.FeatureProductRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeatureService {

    @Autowired
    FeatureProductRepo featureProductRepo;
    @Autowired
    DecodeTokenService decodeToken;


    public void feature_not_pay_service(HttpServletRequest _request, String _id_manager_size_color){
        String _id_user = decodeToken.getTokenFromCookies(_request);
        FeatureProductEntity _feature_product_entity = featureProductRepo.get_feature_with_id_user_and_id_manager_size_color(_id_user,_id_manager_size_color);
        if(_feature_product_entity.get_count_add_card()!=null){
            _feature_product_entity.set_count_add_card(_feature_product_entity.get_count_add_card()+1);
        }else{
            _feature_product_entity.set_count_add_card(1);
        }
        featureProductRepo.save(_feature_product_entity);

    }


    public void  feature_apply_card(String _id_user, String _id_card){
        List<FeatureProductEntity> a  = featureProductRepo.get_feature_with_id_card_and_id_user(_id_card,_id_user).stream().map(t->{
             FeatureProductEntity _f = FeatureProductEntity.builder()
                     ._id(t.get("_id", String.class))
                     ._id_manager_size_color(t.get("_id_manager_size_color", String.class))
                     ._id_user(t.get("_id_user", String.class))
                     ._count_clich(t.get("_count_clich") != null ? t.get("_count_clich", Integer.class) : null)
                     ._count_pay_product(t.get("_count_pay_product") != null ? t.get("_count_pay_product", Integer.class)+1 : 1)
                     ._month_pay_(t.get("_month_pay_") != null ? t.get("_month_pay_", Byte.class) : null)
                     ._last_purchase(t.get("_last_purchase") != null ? t.get("_last_purchase", Integer.class) : null)
                     ._count_cancel(t.get("_count_cancel") != null ? t.get("_count_cancel", Integer.class) : null)
                     ._count_return(t.get("_count_return") != null ? t.get("_count_return", Integer.class) : null)
                     ._count_add_card(t.get("_count_add_card") != null ? t.get("_count_add_card", Integer.class) : null)
                     ._discount(t.get("_discount") != null ? t.get("_discount", Boolean.class) : null)
                     ._day_delete_table(t.get("_day_delete_table") != null ? t.get("_day_delete_table", Timestamp.class).toLocalDateTime() : null)
                     .build();
             featureProductRepo.save(_f);
            return _f;
        }).toList();
    }



}
