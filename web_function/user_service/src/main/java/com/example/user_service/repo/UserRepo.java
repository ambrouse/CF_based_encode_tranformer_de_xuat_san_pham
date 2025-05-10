package com.example.user_service.repo;

import com.example.user_service.entity.UserEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity,String> {

    @Query(value = """
            select u._name as _name_user, u._age as _age_user, u._time_of_birth as _time_of_birth_user, u._img_user as _img_user,
            u._email as _gmail, u._phone_number as _phone_number, a._id as _id_address, a._name as _name_address, u._job as _job_user
            from _user u
            join _address a on a._id_user = u._id
            where u._day_delete_table is null and u._id = :_id_user and a._day_delete_tabel is null
            """,nativeQuery = true)
    List<Tuple> get_user_with_id(@Param("_id_user") String _id_user);
}
