package com.example.product_service.repo;

import com.example.product_service.entity.ImgProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImgProductRepo extends JpaRepository<ImgProductEntity,String> {

    @Query(value = "select _id " +
            "from _img_product " +
            "where _id_manager_size_color = :_id_manager_size_color",nativeQuery = true)
    List<String> get_all_img_product(@Param("_id_manager_size_color") String _id_manager_size_color);
}
