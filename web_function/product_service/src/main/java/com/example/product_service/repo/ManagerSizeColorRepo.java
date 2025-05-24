package com.example.product_service.repo;

import com.example.product_service.entity.ManagerSizeColorEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerSizeColorRepo extends JpaRepository<ManagerSizeColorEntity,String> {

    @Query(value = "select ma._id as _id_manager_size_color, s._id as _id_size, c._id as _id_color, " +
            "s._name as _name_size, c._name as _name_color, ma._sales, ma._inventory, ma._rate " +
            "from _manager_size_color ma, _size s, _color c " +
            "where ma._id_product = :_id_product and s._id = ma._id_size and c._id = ma._id_color",nativeQuery = true)
    List<Tuple> get_manager_with_product(@Param("_id_product") String _id_product);
}
