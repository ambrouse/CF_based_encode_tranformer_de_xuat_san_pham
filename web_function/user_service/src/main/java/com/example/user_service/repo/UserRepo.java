package com.example.user_service.repo;

import com.example.user_service.entity.UserEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity,String> {

    @Query(value = """
            select a._id, a._name
            from _address a
            where a._id_user = :_id_user and a._day_delete_tabel is null
            """,nativeQuery = true)
    List<Tuple> get_all_address(@Param("_id_user") String _id_user);


//    @Query(value = """
//            select u._img_user
//            from _user u
//            where u._id = :_id_user
//            """,nativeQuery = true)
//    byte[] get_img_user(@Param("_id_user") String _id_user);
}
