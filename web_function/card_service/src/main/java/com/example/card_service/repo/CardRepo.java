package com.example.card_service.repo;

import com.example.card_service.entity.CardEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepo extends JpaRepository<CardEntity,String> {

    @Query(value = """
            select c._id as _id, c._status_card,
            c._total_price, c._day_card, c._day_update_status, c._phone_number, c._shipping_fee
            from _card c
            where c._id_user = :_id_user and c._day_delete_table is null and c._status_card = 0
            ORDER BY c._day_card DESC
            """
            ,nativeQuery = true)
    Tuple get_card_with_id_user(@Param("_id_user") String _id_user);


    @Query(value = """
            select _id, _id_user, _id_payment_method, _id_address, _status_card, _status_payment,
            _total_price, _day_card, _day_update_status, _phone_number, _shipping_fee, _day_delete_table
            from _card c
            where c._status_card = 0 and c._day_delete_table is null and c._id_user = :_id_user
            """,nativeQuery = true)
    CardEntity get_card_not_pay_with_id_user(@Param("_id_user") String _id_user);


}
