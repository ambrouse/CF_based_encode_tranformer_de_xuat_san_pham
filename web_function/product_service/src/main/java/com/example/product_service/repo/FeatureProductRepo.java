package com.example.product_service.repo;

import com.example.product_service.entity.FeatureProductEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeatureProductRepo extends JpaRepository<FeatureProductEntity,String> {

    @Query(value = """
            select _id,_id_manager_size_color,_id_user,_count_clich,_count_pay_product,_month_pay_,_last_purchase,
            _count_cancel,_count_return,_count_add_card,_discount,_day_delete_table
            from _feature_product f
            where f._day_delete_table is null and f._id_user = :_id_user and f._id_manager_size_color = :_id_manager_size_color
            """,nativeQuery = true)
    FeatureProductEntity update_feature_product_click(@Param("_id_user") String _id_user,
                                       @Param("_id_manager_size_color") String _id_manager_size_color);

    @Query(value = """
            select f._id as _id, m._id as _id_manager_size_color, u._name as _name_user, u._age as _age, MONTH(u._time_of_birth) AS _month, u._job as _job, u._sex as _sex, u._mean_money_payment as _total_price_buy_product,
           f._count_clich as _count_click, _search_history_custom._search_value as _search_value, _all_pay_product_count._count_all_pay_product as _total_count_product, month(getdate()) as month_pay_product,
           _pay_this_product_count._count_pay_this_product as _count_pay_product, _name_product_last_buy, _name_product_last_buy._name_product_last_buy as _name_product_last_buy,
           _last_purchase._last_purchase as _last_purchase, f._count_add_card as _count_not_pay, m._rate as _rate, _coment_product._coment_product as _coment_product, dis._discount_percent as _discount, p._name as _name_product,
           t._name as _type, se._name as _seasion, co._name as _color, b._name as _brand, st._name as _style, s._name as _size, m._sales as _salse, p._price as _price, p._desription as _description
           from _feature_product as f
           LEFT JOIN _manager_size_color as m on m._id = f._id_manager_size_color
           LEFT JOIN _user as u on u._id = f._id_user
           LEFT JOIN _discount_product as dis on dis._id_manager_size_color = f._id_manager_size_color
           LEFT JOIN _product as p on p._id = m._id_product
           LEFT JOIN _type as t on t._id = p._id_type
           LEFT JOIN _style as st on st._id = p._id_style
           LEFT JOIN _seasion as se on se._id = p._id_seasion
           LEFT JOIN _color as co on co._id = m._id_color
           LEFT JOIN _brand as b on b._id = p._id_brand
           LEFT JOIN _size as s on s._id = m._id_size
           OUTER APPLY (
            select STRING_AGG(_search_content, ', ')  as _search_value
            from  (
                select top 3 h._content as _search_content
                from _search_history h
                where h._id_user = f._id_user
            order by h._day_search DESC
            )as _search
           )as _search_history_custom

           OUTER APPLY (
            select sum(cmn._count) as _count_all_pay_product
            from _card as c
            join _card_mini as cmn on cmn._id_card = c._id
            where c._id_user = f._id_user and c._status_card>0
           )as _all_pay_product_count

           OUTER APPLY (
            select sum(cmn._count) as _count_pay_this_product
            from _card as c
            join _card_mini as cmn on cmn._id_card = c._id and cmn._id_manager_size_color = f._id_manager_size_color
            where c._id_user = f._id_user and c._status_card>0
           )as _pay_this_product_count

           OUTER APPLY (
            select top 1 DATEDIFF(DAY, c._day_card, getdate()) as _last_purchase
            from _card as c
            join _card_mini as cmn on cmn._id_card = c._id and cmn._id_manager_size_color = f._id_manager_size_color
            where c._id_user = f._id_user and c._status_card>0
            order by c._day_card desc
           )as _last_purchase

           OUTER APPLY (
            select top 1 p._name as _name_product_last_buy
            from _card as c
            join _card_mini as cmn on cmn._id_card = c._id
            join _manager_size_color m on m._id = cmn._id_manager_size_color
            join _product p on p._id = m._id_product
            where c._id_user = f._id_user and c._status_card>0
            order by c._day_card desc
           )as _name_product_last_buy

           OUTER APPLY (
            select STRING_AGG(_coment_custom_child._coment_value, ', ')  as _coment_product
            from (select top 3 c._content as _coment_value from _coment c where c._id_manager_size_color=f._id_manager_size_color) as _coment_custom_child
           ) as _coment_product
           where f._id_user = :_id_user
            """,nativeQuery = true)
    List<Tuple> get_all_feature_with_id_user(@Param("_id_user") String _id_user);
}
