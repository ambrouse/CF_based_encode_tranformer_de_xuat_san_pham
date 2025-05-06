package com.example.card_service.repo;

import com.example.card_service.entity.AddressEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepo extends JpaRepository<AddressEntity,String> {

    @Query(value = """
            select _id, _name
            from _address a
            where a._id_user = :_id_user and a._day_delete_tabel is null
            """,nativeQuery = true)
    List<Tuple> get_address_with_id_user(@Param("_id_user") String _id_user);
}
