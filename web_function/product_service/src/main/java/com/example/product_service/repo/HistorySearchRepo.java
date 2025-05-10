package com.example.product_service.repo;

import com.example.product_service.entity.HistorySearchEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistorySearchRepo extends JpaRepository<HistorySearchEntity,String> {

    @Query(value = "select _id, _id_user, _content, _day_search " +
            "from _search_history " +
            "where _id_user = :_id " +
            "ORDER BY _day_search DESC",nativeQuery = true)
    List<Tuple> get_with_id_user(@Param("_id") String _id);


    @Query(value = """
            select top 3 _content
            from _search_history
            where _id_user = :_id_user and _day_delete_table is null
            ORDER BY _day_search ASC
            """,nativeQuery = true)
    List<String> get_3_search(@Param("_id_user") String _id_user);
}
