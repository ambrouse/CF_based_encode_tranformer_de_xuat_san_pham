package com.example.product_service.repo;

import com.example.product_service.entity.UserEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<UserEntity,String> {

    @Query(value = """
            select u._name, count(_img_user) as _id_img_user
            from _user u
            where u._id = :_id_user
            group by u._name
            """,nativeQuery = true)
    Tuple get_user_width_id(@Param("_id_user") String _id_user);
}
