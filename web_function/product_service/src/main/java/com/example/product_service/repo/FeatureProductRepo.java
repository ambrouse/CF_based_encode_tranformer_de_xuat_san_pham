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
            select
            	ma._id as _id_manager_size_color, u._name as _name_user, u._age as _age, month(u._time_of_birth) as _month,u._job as _job,u._sex,
            	u._mean_money_payment as _total_price_buy_product,
            	f._count_clich as _count_click, _search_history_custom._search_value, f._count_pay_product as _count_pay_product,
            	month(getdate()) as month_pay_product,sum(cmn._count) as _total_count_product, _name_product_last_buy_custom._name_product_last_buy,
            	DATEDIFF(day, CAST(c._day_card AS DATE), GETDATE()) as _last_purchase, f._count_add_card as _count_not_pay,ma._rate as _rate,
            	_coment_custom_root._coment_product,_discount_product_custom._discount, p._name as _name_product,
            	t._name as _type, sea._name as _seasion, co._name as _color, bra._name as _brand, st._name as _style, s._name as _size,
            	ma._sales as _sales, p._price, p._desription as _description
            from _card_mini cmn
            join _card c on c._id = cmn._id_card and c._status_card > 0
            join _feature_product f on f._id_user = :_id_user
            join _manager_size_color ma on ma._id = f._id_manager_size_color
            join _product p on p._id = ma._id_product
            join _user u on u._id = :_id_user
            join _type t on t._id = p._id_type
            join _style st on st._id = p._id_style
            join _seasion sea on sea._id = p._id_seasion
            join _brand bra on bra._id = p._id_brand
            join _color co on co._id = ma._id_color
            join _size s on s._id = ma._id_size
            OUTER APPLY (
            	select STRING_AGG(_search_content, ', ')  as _search_value
            	from  (
            		select top 3 h._content as _search_content
            		from _search_history h
            		where h._id_user = :_id_user
            		order by h._day_search DESC
            		)as _search
            ) as _search_history_custom
            OUTER APPLY (
            	select STRING_AGG(_coment_custom_child._coment_value, ', ')  as _coment_product
            	from (select top 3 c._content as _coment_value from _coment c where c._id_manager_size_color=ma._id) as _coment_custom_child
            ) as _coment_custom_root
            OUTER APPLY (
            	select count(d._discount_percent) as _discount from _discount_product d where d._id_manager_size_color = ma._id
            ) as _discount_product_custom
            OUTER APPLY (
            	select top 1 pro._name as _name_product_last_buy
            	from _card_mini cmini
            	join _card ca on ca._id = cmini._id_card and ca._status_card>0
            	join _manager_size_color mana on mana._id = cmini._id_manager_size_color
            	join _product pro on pro._id = mana._id_product
            	where ca._id_user = :_id_user
            	order by ca._day_card
            ) as _name_product_last_buy_custom
            where c._id_user = :_id_user
            GROUP BY u._name, u._age, u._time_of_birth,u._job,u._sex, u._mean_money_payment,
            f._count_clich, _search_history_custom._search_value,f._count_pay_product,
            f._id,f._id_manager_size_color,_name_product_last_buy_custom._name_product_last_buy, c._day_card,f._count_add_card, ma._rate,
            _coment_custom_root._coment_product,
            _discount_product_custom._discount,p._name,t._name, st._name, co._name,s._name,bra._name,sea._name,ma._sales,p._price, p._desription,ma._id
            """,nativeQuery = true)
    List<Tuple> get_all_feature_with_id_user(@Param("_id_user") String _id_user);
}
