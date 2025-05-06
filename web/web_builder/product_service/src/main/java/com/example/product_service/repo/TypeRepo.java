package com.example.product_service.repo;

import com.example.product_service.entity.TypeEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepo extends JpaRepository<TypeEntity,String> {
    @Query(value = "select _id, _name " +
            "from _type ",nativeQuery = true)
    List<Tuple> get_all_id_and_name();
}
