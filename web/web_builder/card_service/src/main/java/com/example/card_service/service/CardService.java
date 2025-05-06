package com.example.card_service.service;


import com.example.card_service.api.ApiRespon;
import com.example.card_service.entity.CardEntity;
import com.example.card_service.entity.CardMiniEntity;
import com.example.card_service.entity.UserEntity;
import com.example.card_service.err_manager.CustomException;
import com.example.card_service.repo.*;
import com.example.card_service.request.AddCardRequest;
import com.example.card_service.request.ApplyCardRequest;
import com.example.card_service.request.CardMiniUpdateRequest;
import com.example.card_service.respon.*;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepo cardRepo;
    @Autowired
    CardMiniRepo cardMiniRepo;
    @Autowired
    FeatureService featureService;
    @Autowired
    DecodeTokenService decodeToken;
    @Autowired
    AddressService addressService;
    @Autowired
    PaymentMethodService paymentMethodService;
    @Autowired
    UserRepo userRepo;



    public ApiRespon<CardsRespon> get_card_service(HttpServletRequest _request){
            String _id_user = decodeToken.getTokenFromCookies(_request);
            Tuple t = cardRepo.get_card_with_id_user(_id_user);
            if(t==null){
                throw new RuntimeException(CustomException.builder()
                        .statusCode(400)
                        .message("Not card")
                        .build());
            }

            CardRespon _cards_respon =  CardRespon.builder()
                    ._id((String) t.get("_id"))
                    ._status_card(t.get("_status_card")==null? 0:(byte) t.get("_status_card"))
                    ._total_price(t.get("_total_price")==null? 0:(int) t.get("_total_price"))
                    ._day_card(t.get("_day_card")==null? null:((Timestamp) t.get("_day_card")).toLocalDateTime())
                    ._day_update_status(t.get("_day_update_status")==null? null: ((Timestamp) t.get("_day_update_status")).toLocalDateTime())
                    ._phone_number((String) t.get("_phone_number"))
                    ._shipping_fee(t.get("_shipping_fee")==null? 0:(int) t.get("_shipping_fee"))
                    .build();

            List<CardMiniRespon> _cards_mini_respon = cardMiniRepo.get_all_minicard_with_id_card(t.get("_id").toString()).stream().map(tu->{
                List<ManagerSizeColorRespon> _manager_size_colors_respon = cardMiniRepo.get_all_size_color_with_card_mini(tu.get("_id_manager_size_color").toString()).stream().map(tup->{
                    return ManagerSizeColorRespon.builder()
                            ._id((String) tup.get("_id"))
                            ._name_color((String) tup.get("_name_color"))
                            ._name_size((String) tup.get("_name_size"))
                            .build();
                }).toList();

                return CardMiniRespon.builder()
                        ._id_card_mini((String) tu.get("_id_card_mini"))
                        ._id_manager_size_color((String) tu.get("_id_manager_size_color"))
                        ._id_color((String) tu.get("_id_color"))
                        ._id_size((String) tu.get("_id_size"))
                        ._name_product((String) tu.get("_name_product"))
                        ._name_color((String) tu.get("_name_color"))
                        ._name_size((String) tu.get("_name_size"))
                        ._count(tu.get("_count")==null? 0:(int) tu.get("_count"))
                        ._price(tu.get("_price")==null? 0:(int) tu.get("_price"))
                        ._id_product((String) tu.get("_id_product"))
                        ._img_product((String) tu.get("_img_product"))
                        ._manager_size_colors_respon(_manager_size_colors_respon)
                        .build();
            }).toList();
            _cards_respon.set_cards_desription(_cards_mini_respon);

            return ApiRespon.<CardsRespon>builder()
                    ._request(200)
                    ._request_desription("get card user api")
                    ._result(CardsRespon.builder()
                            ._cards_respon(_cards_respon)
                            ._address_respon(addressService.get_address_service(_id_user))
                            ._payment_method_respon(paymentMethodService.get_payment_method_service())
                            .build())
                    .build();
    }

    public ApiRespon<CardMiniDeleteRespon> delete_card_mini_respon(String _id_card_mini, HttpServletRequest _request){


        try {
            CardMiniEntity _card_mini_entity = cardMiniRepo.findById(_id_card_mini).get();
            CardEntity _card_entity = cardRepo.findById(_card_mini_entity.get_id_card()).get();
            _card_mini_entity.set_day_delete_table(LocalDateTime.now());
            featureService.feature_not_pay_service(_request,_card_mini_entity.get_id_manager_size_color());
            cardMiniRepo.save(_card_mini_entity);

            return ApiRespon.<CardMiniDeleteRespon>builder()
                    ._request(200)
                    ._request_desription("delete mini card api")
                    ._result(CardMiniDeleteRespon.builder()
                            ._status("delete ok!")
                            .build())
                    .build();
        } catch (RuntimeException e) {
            throw  new RuntimeException(CustomException.builder()
                    .message("delete mini card err: \n"+e)
                    .statusCode(400)
                    .build());
        }


    }

    public ApiRespon<CardMiniUpdateRespon> update_card_mini_request(CardMiniUpdateRequest _card_mini_update_request){

        try {
            CardMiniEntity _card_mini_entity = cardMiniRepo.findById(_card_mini_update_request.get_id_card_mini()).get();
            if(_card_mini_update_request.get_id_manager_size_color()!=null){
                _card_mini_entity.set_id_manager_size_color(_card_mini_update_request.get_id_manager_size_color());
            }
            if(_card_mini_update_request.get_count()!=0){
                _card_mini_entity.set_count(_card_mini_update_request.get_count());
            }
            cardMiniRepo.save(_card_mini_entity);

            return ApiRespon.<CardMiniUpdateRespon>builder()
                    ._request(200)
                    ._request_desription("update card mini api")
                    ._result(CardMiniUpdateRespon.builder()
                            ._status("update submit")
                            .build())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(CustomException.builder()
                    .statusCode(400)
                    .message("update card mini api err: "+e)
                    .build());
        }

    }

    public ApiRespon<ApplyCardRespon> apply_card_service(ApplyCardRequest _apply_card_request, HttpServletRequest _request){
            System.out.println(_apply_card_request);
            String _id_user = decodeToken.getTokenFromCookies(_request);
            CardEntity _card_entity = cardRepo.findById(_apply_card_request.get_id_card()).get();
            _card_entity.set_status_card((byte) 1);
            _card_entity.set_day_update_status(LocalDateTime.now());
            _card_entity.set_id_address(_apply_card_request.get_id_address());
            _card_entity.set_id_payment_method(_apply_card_request.get_id_payment_method());
            _card_entity.set_status_payment((byte) 0);
            _card_entity.set_phone_number(_apply_card_request.get_phone_number());
            _card_entity.set_day_card(LocalDateTime.now());



            List<Tuple> tuples = cardMiniRepo.get_all_minicard_with_id_card(_card_entity.get_id());

            if(tuples.isEmpty()){
                throw new RuntimeException(CustomException.builder()
                        .statusCode(400)
                        .message("Not product")
                        .build());
            }
            int total = 0;
            for(Tuple t : tuples){
                CardMiniEntity cardMiniEntity = cardMiniRepo.findById((String) t.get("_id_card_mini")).get();
                cardMiniEntity.set_price((int) cardMiniRepo.get_price_with_id_manager_size_color(cardMiniEntity.get_id_manager_size_color()).get("_price"));
                total+=(cardMiniEntity.get_price()*cardMiniEntity.get_count());
                cardMiniRepo.save(cardMiniEntity);
            }
            _card_entity.set_total_price(total);
            cardRepo.save(_card_entity);
            featureService.feature_apply_card(_id_user,_apply_card_request.get_id_card());
            UserEntity _user_entity = userRepo.findById(_id_user).get();
            _user_entity.set_mean_money_payment(_user_entity.get_mean_money_payment()==null?_card_entity.get_total_price():
                    (int)(_user_entity.get_mean_money_payment()+_card_entity.get_total_price())/2);
            userRepo.save(_user_entity);
            return ApiRespon.<ApplyCardRespon>builder()
                    ._request(200)
                    ._request_desription("Apply card api")
                    ._result(ApplyCardRespon.builder()
                            ._status("Apply submit")
                            .build())
                    .build();
    }

    public ApiRespon<AddCardRespon> add_card_service(AddCardRequest _add_card_request, HttpServletRequest _request){

        try {
            String _id_user = decodeToken.getTokenFromCookies(_request);
            CardEntity _card_entity = cardRepo.get_card_not_pay_with_id_user(_id_user);
            if(_card_entity != null){
                CardMiniEntity _card_mini_entity = cardMiniRepo.get_card_mini_with_id_size_color_and_id_user_and_id_card(_add_card_request.get_id_manager_size_color(),_id_user,_card_entity.get_id());
                System.out.println((_card_mini_entity));
                System.out.println((_card_entity));
                if(_card_mini_entity != null){
                    _card_mini_entity.set_count(_card_mini_entity.get_count()+1);
                    cardMiniRepo.save(_card_mini_entity);
                }else{
                    _card_mini_entity = CardMiniEntity.builder()
                            ._id_card(_card_entity.get_id())
                            ._count(1)
                            ._price(_add_card_request.get_price())
                            ._id_manager_size_color(_add_card_request.get_id_manager_size_color())
                            .build();
                    cardMiniRepo.save(_card_mini_entity);
                }
                _card_entity.set_total_price(_card_entity.get_total_price() + _add_card_request.get_price());
                cardRepo.save(_card_entity);
            }else{
                _card_entity = CardEntity.builder()
                        ._status_card((byte)0)
                        ._id_user(_id_user)
                        ._total_price(0)
                        ._shipping_fee(30000)
                        .build();
                CardEntity _card_entity1 = cardRepo.save(_card_entity);

                CardMiniEntity _card_mini_entity = CardMiniEntity.builder()
                        ._id_manager_size_color(_add_card_request.get_id_manager_size_color())
                        ._id_card(_card_entity1.get_id())
                        ._price(_add_card_request.get_price())
                        ._count(1)
                        .build();
                cardMiniRepo.save(_card_mini_entity);
            }
            return ApiRespon.<AddCardRespon>builder()
                    ._request(200)
                    ._request_desription("Add card api")
                    ._result(AddCardRespon.builder()
                            ._status("add card submit")
                            .build())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(CustomException.builder()
                    .statusCode(400)
                    .message("Add card is err: \n"+e)
                    .build());
        }
    }

}
