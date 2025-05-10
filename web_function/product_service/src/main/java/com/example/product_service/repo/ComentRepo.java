package com.example.product_service.repo;

import com.example.product_service.entity.ComentEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentRepo extends JpaRepository<ComentEntity,String> {

    @Query(value = "select top 10 c._id as _id, c._content as _coment, u._name as _name_user, u._img_user as _img_user, c._id_user as _id_user " +
            "from _user u, _coment c " +
            "where c._id_manager_size_color = :_id_manager_size_color and u._id = c._id_user and c._day_delete_table is null and u._day_delete_table is null " +
            "ORDER BY c._day_coment DESC",nativeQuery = true)
    List<Tuple> get_coment(@Param("_id_manager_size_color") String _id_manager_size_color);




}
