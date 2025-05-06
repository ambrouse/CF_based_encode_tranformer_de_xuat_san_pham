package com.example.product_service.repo;

import com.example.product_service.entity.ImgComentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImgComentRepo extends JpaRepository<ImgComentEntity,String> {

    @Query(value = "select _id " +
            "from _img_coment " +
            "where _id_coment = :_id_coment",nativeQuery = true)
    String get_img_coment(@Param("_id_coment") String _id_coment);
}
