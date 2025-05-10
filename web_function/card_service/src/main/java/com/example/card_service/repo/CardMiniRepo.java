package com.example.card_service.repo;

import com.example.card_service.entity.CardMiniEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardMiniRepo extends JpaRepository<CardMiniEntity,String> {

    @Query(value = """
            select cmn._id as _id_card_mini, m._id as _id_manager_size_color, c._id as _id_color,
            s._id as _id_size, c._name as _name_color, s._name as _name_size, cmn._count, p._price as _price,
            (select top 1 p._name from _product p where p._id = m._id_product) as _name_product,
            (select top 1 p._id from _product p where p._id = m._id_product) as _id_product,
            (select top 1 img._id  from _img_product img where  img._id_manager_size_color = m._id) as _img_product
            from _card_mini cmn
            join _manager_size_color m on cmn._id_manager_size_color = m._id
            join _product p on p._id = m._id_product
            join _color c on m._id_color = c._id
            join _size s on m._id_size = s._id
            Where cmn._id_card = :_id_card and cmn._day_delete_table is null
            """,nativeQuery = true)
    List<Tuple> get_all_minicard_with_id_card(@Param("_id_card") String _id_card);


    @Query(value = """
            select m._id as _id, s._name as _name_size, c._name as _name_color
            from _manager_size_color m
            Join _size s on m._id_size = s._id
            Join _color c on m._id_color = c._id
            Where m._day_delete_table is null and m._id_product = (select p._id from _product p, _manager_size_color ma where p._id = ma._id_product and ma._id = :_id_manager_size_color)
            """,
    nativeQuery = true)
    List<Tuple> get_all_size_color_with_card_mini(@Param("_id_manager_size_color") String _id_card_mini);

    @Query(value = """
            select top 1 cmn._id, cmn._id_card, cmn._id_manager_size_color, p._price as _price, cmn._count, cmn._day_delete_table
            from _card_mini cmn
            join _card c on c._id = cmn._id_card and c._id = :_id_card
            join _manager_size_color m on m._id = :_id_manager_size_color
            join _product p on p._id = m._id_product
            where cmn._id_manager_size_color = :_id_manager_size_color
            and c._id_user = :_id_user
            and cmn._day_delete_table is null
            """,nativeQuery = true)
    CardMiniEntity get_card_mini_with_id_size_color_and_id_user_and_id_card(@Param("_id_manager_size_color") String _id_manager_size_color,
                                                                            @Param("_id_user") String _id_user,
                                                                            @Param("_id_card") String _id_card);

    @Query(value = """
            select p._price
            from _manager_size_color m
            join _product p on m._id_product = p._id
            where m._id = :_id_manager_size_color
            """,nativeQuery = true)
    Tuple get_price_with_id_manager_size_color(@Param("_id_manager_size_color") String _id_manager_size_color);
}
