package com.example.card_service.repo;

import com.example.card_service.entity.PaymentMethodEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethodEntity,String> {

    @Query(value = """
            select _id, _name
            from _payment_method p
            where p._day_delete_table is null
            """,nativeQuery = true)
    List<Tuple> get_payment_method_with_id_user();
}
