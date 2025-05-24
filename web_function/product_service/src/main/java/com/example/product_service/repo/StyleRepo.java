package com.example.product_service.repo;

import com.example.product_service.entity.StyleEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StyleRepo extends JpaRepository<StyleEntity,String> {
    @Query(value = "select _id, _name " +
            "from _style ",nativeQuery = true)
    List<Tuple> get_all_id_and_name();
}
