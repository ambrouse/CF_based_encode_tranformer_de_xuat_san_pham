package com.example.auth_service.repo;


import com.example.auth_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<UserEntity,String> {

    @Query(value = "select _id " +
            "from _user " +
            "where _email = :_gmail",nativeQuery = true)
    String get_user_with_gmail(@Param("_gmail") String _gmail);

    @Query(value = "select _id " +
            "from _user " +
            "where _phone_number = :_phone",nativeQuery = true)
    String get_user_with_phone(@Param("_phone") String _phone);

    @Query(value = "select _id " +
            "from _user " +
            "where _id_authen = :_id_auth",nativeQuery = true)
    String get_user_with_id_auth(@Param("_id_auth") String _id_auth);

}
