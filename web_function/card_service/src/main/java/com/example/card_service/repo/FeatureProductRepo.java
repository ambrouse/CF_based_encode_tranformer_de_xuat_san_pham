package com.example.card_service.repo;

import com.example.card_service.entity.FeatureProductEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeatureProductRepo extends JpaRepository<FeatureProductEntity,String> {

    @Query(value = """
                select _id, _id_manager_size_color, _id_user, _count_clich, _count_pay_product, _month_pay_,
                _last_purchase, _count_cancel, _count_return, _count_add_card, _discount, _day_delete_table
                from _feature_product f
                where f._id_user = :_id_user and f._id_manager_size_color = :_id_manager_size_color and f._day_delete_table is null
            """,nativeQuery = true)
    FeatureProductEntity get_feature_with_id_user_and_id_manager_size_color(@Param("_id_user") String _id_user,
                                                                            @Param("_id_manager_size_color") String _id_manager_size_color);


    @Query(value = """
            select f._id,f._id_manager_size_color,f._id_user,f._count_clich,f._count_pay_product,f._month_pay_,f._last_purchase,
            f._count_cancel,f._count_return,f._count_add_card,f._discount,f._day_delete_table
            from _feature_product f
            join _card_mini cmn on _id_card = :_id_card
            where f._id_manager_size_color = cmn._id_manager_size_color and f._id_user = :_id_user
            """,nativeQuery = true)
    List<Tuple> get_feature_with_id_card_and_id_user(@Param("_id_card") String _id_card,
                                         @Param("_id_user") String _id_user);
}
