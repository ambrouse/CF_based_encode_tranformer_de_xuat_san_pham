package com.example.product_service.service;

import com.example.product_service.api.ApiRespon;
import com.example.product_service.entity.*;
import com.example.product_service.repo.*;
import com.example.product_service.respon.CustomType;
import com.example.product_service.respon.SearchDefaultRespon;
import com.example.product_service.respon.SearchRespon;
import com.example.product_service.respon.SearchsRespon;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    @Autowired
    TypeRepo typeRepo;
    @Autowired
    StyleRepo styleRepo;
    @Autowired
    BrandRepo brandRepo;
    @Autowired
    SeasionRepo seasionRepo;
    @Autowired
    MaterialRepo materialRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ManagerSizeColorRepo managerSizeColorRepo;
    @Autowired
    HistorySearchRepo historySearchRepo;
    @Autowired
    DecodeToken decodeToken;

    public ApiRespon<SearchsRespon> search_service(String _name_product,
                                                   String _type_product,
                                                   String _brand_product,
                                                   String _seasion_product,
                                                   String _style_product,
                                                   String _material_product,
                                                   String _sort_id, HttpServletRequest _request){

        List<Tuple> tupleLits = productRepo.search(_name_product, _type_product, _brand_product, _seasion_product, _style_product, _material_product, _sort_id);

        List<SearchRespon> searchRespons = tupleLits.stream().map(_t -> {
            SearchRespon _res = new SearchRespon();
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
        history_search_update(_name_product,_request);

        return ApiRespon.<SearchsRespon>builder()
                ._request(200)
                ._request_desription("search api")
                ._result(SearchsRespon.builder()
                        .searchRespons(searchRespons)
                        .build())
                .build();
    }


    public ApiRespon<SearchDefaultRespon> search_default_service(){
        List<Tuple> _tuple_type = typeRepo.get_all_id_and_name();
        List<Tuple> _tuple_style = styleRepo.get_all_id_and_name();
        List<Tuple> _tuple_brand = brandRepo.get_all_id_and_name();
        List<Tuple> _tuple_seasion = seasionRepo.get_all_id_and_name();
        List<Tuple> _tuple_material = materialRepo.get_all_id_and_name();

        List<CustomType> list_type_product = _tuple_type.stream().map(t -> new CustomType(
                t.get("_id",String.class),
                t.get("_name",String.class)
        )).toList();

        List<CustomType> list_style_product = _tuple_style.stream().map(t -> new CustomType(
                t.get("_id",String.class),
                t.get("_name",String.class)
        )).toList();
        List<CustomType> list_brand_product = _tuple_brand.stream().map(t -> new CustomType(
                t.get("_id",String.class),
                t.get("_name",String.class)
        )).toList();
        List<CustomType> list_seasion_product = _tuple_seasion.stream().map(t -> new CustomType(
                t.get("_id",String.class),
                t.get("_name",String.class)
        )).toList();
        List<CustomType> list_material_product = _tuple_material.stream().map(t -> new CustomType(
                t.get("_id",String.class),
                t.get("_name",String.class)
        )).toList();
        return ApiRespon.<SearchDefaultRespon>builder()
                ._request(200)
                ._request_desription("search default api")
                ._result(SearchDefaultRespon.builder()
                        ._list_brand_product(list_brand_product)
                        ._list_seasion_product(list_seasion_product)
                        ._list_material_product(list_material_product)
                        ._list_style_product(list_style_product)
                        ._list_type_product(list_type_product)
                        .build())
                .build();
    }

    public void history_search_update(String _content_search,HttpServletRequest _request){
        String _id_user = decodeToken.getTokenFromCookies(_request);
        List<Tuple> _t = historySearchRepo.get_with_id_user(_id_user).stream().toList();
        List<HistorySearchEntity> historySearchEntities = _t.stream().map(_tu -> new HistorySearchEntity(
                _tu.get("_id", String.class),
                _tu.get("_id_user", String.class),
                _tu.get("_content", String.class),
                _tu.get("_day_search", Timestamp.class).toLocalDateTime(),
                null
        )).toList();
        if(historySearchEntities.size()!=10){
            HistorySearchEntity historySearchEntity = HistorySearchEntity.builder()
                    .idUser(_id_user)
                    .content(_content_search)
                    .daySearch(LocalDateTime.now())
                    .dayDeleteTable(null)
                    .build();
            HistorySearchEntity _h = historySearchRepo.save(historySearchEntity);
        }else{
            historySearchEntities.getFirst().setDaySearch(LocalDateTime.now());
            historySearchEntities.getFirst().setContent(_content_search);
            historySearchRepo.save(historySearchEntities.getFirst());
        }
    }
}
