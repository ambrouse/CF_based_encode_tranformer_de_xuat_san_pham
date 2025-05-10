package com.example.product_service.repo;

import com.example.product_service.entity.BrandEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepo extends JpaRepository<BrandEntity,String> {

    @Query(value = "select _id, _name " +
            "from _brand " +
            "WHERE _day_delete_table = NULL",nativeQuery = true)
    List<Tuple> get_all_id_and_name();
}
