package com.example.auth_service.repo;


import com.example.auth_service.entity.AuthEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthRepo extends JpaRepository<AuthEntity,String> {

    @Query(value = "select a._id, r._name " +
            "from _authen a, _role r " +
            "where a._name = :_email and a._password = :_password and a._id_role = r._id",nativeQuery = true)
    Tuple check_login(@Param("_email") String _email,
                      @Param("_password") String _password);

}
