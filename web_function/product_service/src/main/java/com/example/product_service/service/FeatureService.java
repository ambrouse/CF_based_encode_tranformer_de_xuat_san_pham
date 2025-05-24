package com.example.product_service.service;

import com.example.product_service.api.ApiRespon;
import com.example.product_service.entity.UserEntity;
import com.example.product_service.repo.FeatureProductRepo;
import com.example.product_service.repo.HistorySearchRepo;
import com.example.product_service.repo.UserRepo;
import com.example.product_service.respon.DeepRespon;
import com.example.product_service.respon.FeatureRespon;
import com.example.product_service.respon.HomePageDeepRespon;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
public class FeatureService {

    @Autowired
    FeatureProductRepo featureProductRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    DecodeToken decodeToken;
    @Autowired
    HistorySearchRepo historySearchRepo;
    @Autowired
    DeepService deepService;

    public List<DeepRespon> product_recommendation_service(HttpServletRequest _request){
        String _id_user = decodeToken.getTokenFromCookies(_request);
        if(_id_user == null){
            return null;
        }
        List<FeatureRespon> _features_respon =  featureProductRepo.get_all_feature_with_id_user(_id_user).stream().map(t->{
            return FeatureRespon.builder()
                    ._id_manager_size_color(getSafeString(t.get("_id_manager_size_color")))
                    ._name_user(getSafeString(t.get("_name_user")))
                    ._age(t.get("_age") != null ? ((Number) t.get("_age")).shortValue() : 0)
                    ._month(t.get("_month") != null ? ((Number) t.get("_month")).shortValue() : 0)
                    ._job(getSafeString(t.get("_job")))
                    ._sex(t.get("_sex") != null && (Boolean) t.get("_sex"))
                    ._total_price_buy_product(t.get("_total_price_buy_product") != null ? ((Number) t.get("_total_price_buy_product")).intValue() : 0)
                    ._count_click(t.get("_count_click") != null ? ((Number) t.get("_count_click")).intValue() : 0)
                    ._search_value(getSafeString(t.get("_search_value")))
                    ._count_pay_product(t.get("_count_pay_product") != null ? ((Number) t.get("_count_pay_product")).intValue() : 0)
                    .month_pay_product(t.get("month_pay_product") != null ? ((Number) t.get("month_pay_product")).intValue() : 0)
                    ._total_count_product(t.get("_total_count_product") != null ? ((Number) t.get("_total_count_product")).intValue() : 0)
                    ._name_product_last_buy(getSafeString(t.get("_name_product_last_buy")))
                    ._last_purchase(t.get("_last_purchase") != null ? ((Number) t.get("_last_purchase")).intValue() : 0)
                    ._cancel(0)
                    ._return(0)
                    ._count_not_pay(t.get("_count_not_pay") != null ? ((Number) t.get("_count_not_pay")).intValue() : 0)
                    ._rate(t.get("_rate") != null ? ((Number) t.get("_rate")).shortValue() : 0)
                    ._coment_product(getSafeString(t.get("_coment_product")))
                    ._discount(t.get("_discount") != null ? ((Number) t.get("_discount")).intValue() : 0)
                    ._name_product(getSafeString(t.get("_name_product")))
                    ._type(getSafeString(t.get("_type")))
                    ._seasion(getSafeString(t.get("_seasion")))
                    ._color(getSafeString(t.get("_color")))
                    ._brand(getSafeString(t.get("_brand")))
                    ._style(getSafeString(t.get("_style")))
                    ._size(getSafeString(t.get("_size")))
                    ._sales(t.get("_salse") != null ? ((Number) t.get("_salse")).intValue() : 0)
                    ._price(t.get("_price") != null ? ((Number) t.get("_price")).intValue() : 0)
                    ._description(getSafeString(t.get("_description")))
                    .build();
        }).toList();
        List<DeepRespon> _deep_respon = deepService.deep_service(ApiRespon.<HomePageDeepRespon>builder()
                ._request(200)
                ._request_desription("get feature api")
                ._result(HomePageDeepRespon.builder()
                        ._features_respon(_features_respon)
                        .build())
                .build());

        return _deep_respon;
    }

    private String getSafeString(Object value) {
        return value != null ? value.toString() : " ";
    }

}
