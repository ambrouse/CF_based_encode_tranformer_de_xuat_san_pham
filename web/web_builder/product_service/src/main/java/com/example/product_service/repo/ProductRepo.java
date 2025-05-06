package com.example.product_service.repo;

import com.example.product_service.entity.ProductEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity,String> {

    @Query(value = """
            SELECT p._id AS _id_product, p._name AS _name_product, p._desription AS _desription_product, t._name AS _type_product, 
                st._name AS _style_product, se._name AS _seasion_product, b._name AS _brand_product, m._name AS _material_product, AVG(ma._rate) as _rate_product, 
                (select top 1 img._id
                        from _img_product img
                        join _manager_size_color mana on mana._id_product = p._id
                        where img._id_manager_size_color = mana._id
                ) as _img_product,
                p._price as _price_product
            FROM _product p 
            JOIN _type t ON p._id_type = t._id 
            JOIN _style st ON p._id_style = st._id 
            JOIN _seasion se ON p._id_seasion = se._id 
            JOIN _brand b ON p._id_brand = b._id 
            JOIN _material m ON p._id_material = m._id 
            JOIN _manager_size_color ma ON p._id = ma._id_product 
            WHERE 
                p._name LIKE CONCAT('%', :_name_product, '%') AND 
                t._name LIKE CONCAT('%', :_type_product, '%') AND 
                st._name LIKE CONCAT('%', :_style_product, '%') AND 
                se._name LIKE CONCAT('%', :_seasion_product, '%') AND 
                b._name LIKE CONCAT('%', :_brand_product, '%') AND 
                m._name LIKE CONCAT('%', :_material_product, '%') 
            GROUP BY 
                p._id, p._name, p._desription, 
                t._name, st._name, se._name, p._price, 
                b._name, m._name
            ORDER BY 
                CASE WHEN :_sort_id = '0' THEN p._price END ASC, 
                CASE WHEN :_sort_id = '1' THEN p._price END DESC, 
                CASE WHEN :_sort_id = '2' THEN p._name END ASC, 
                CASE WHEN :_sort_id = '3' THEN p._name END DESC
            """,nativeQuery = true)
    List<Tuple> search(@Param("_name_product") String _name_product,
                       @Param("_type_product") String _type_product,
                       @Param("_brand_product") String _brand_product,
                       @Param("_material_product") String _material_product,
                       @Param("_seasion_product") String _seasion_product,
                       @Param("_style_product") String _style_product,
                       @Param("_sort_id") String _sort_id);

    @Query(value =
            """
                SELECT p._id AS _id_product,  p._name AS _name_product, p._desription AS _desription_product,
                    t._name AS _type_product, st._name AS _style_product, se._name AS _seasion_product, b._name AS _brand_product,
                    m._name AS _material_product, AVG(ma._rate) as _rate_product,
                    (select top 1 img._id
                        from _img_product img
                        join _manager_size_color mana on mana._id_product = p._id
                        where img._id_manager_size_color = mana._id
                    ) as _img_product,
                    p._price as _price_product
                FROM _product p
                JOIN _type t ON p._id_type = t._id
                JOIN _style st ON p._id_style = st._id
                JOIN _seasion se ON p._id_seasion = se._id
                JOIN _brand b ON p._id_brand = b._id
                JOIN _material m ON p._id_material = m._id
                JOIN _manager_size_color ma ON p._id = ma._id_product
                GROUP BY
                    p._id, p._name, p._desription,
                    t._name, st._name, se._name, p._price,
                    b._name, m._name
                ORDER BY p._id DESC
                """, nativeQuery = true
    )
    List<Tuple> home();

    @Query(value =
            """
                SELECT p._id AS _id_product,  p._name AS _name_product, p._desription AS _desription_product,
                    t._name AS _type_product, st._name AS _style_product, se._name AS _seasion_product, b._name AS _brand_product,
                    m._name AS _material_product, AVG(ma._rate) as _rate_product,
                    (select top 1 img._id
                        from _img_product img
                        join _manager_size_color mana on mana._id_product = p._id
                        where img._id_manager_size_color = mana._id
                    ) as _img_product,
                    p._price as _price_product
                FROM _product p
                JOIN _type t ON p._id_type = t._id
                JOIN _style st ON p._id_style = st._id
                JOIN _seasion se ON p._id_seasion = se._id
                JOIN _brand b ON p._id_brand = b._id
                JOIN _material m ON p._id_material = m._id
                JOIN _manager_size_color ma ON p._id = ma._id_product and ma._id in :_list_id_manager_product
                GROUP BY
                    p._id, p._name, p._desription,
                    t._name, st._name, se._name, p._price,
                    b._name, m._name
                """, nativeQuery = true
    )
    List<Tuple> home_recoment(@Param("_list_id_manager_product") List<String> _list_id_product) ;

    @Query(value =
            "SELECT top 5 " +
                    "p._id AS _id_product, " +
                    "p._name AS _name_product, " +
                    "p._desription AS _desription_product, " +
                    "t._name AS _type_product, " +
                    "st._name AS _style_product, " +
                    "se._name AS _seasion_product, " +
                    "b._name AS _brand_product, " +
                    "m._name AS _material_product, " +
                    "AVG(ma._rate) as _rate_product, "+
                    "p._price as _price_product "+
                    "FROM _product p " +
                    "JOIN _type t ON p._id_type = t._id " +
                    "JOIN _style st ON p._id_style = st._id " +
                    "JOIN _seasion se ON p._id_seasion = se._id " +
                    "JOIN _brand b ON p._id_brand = b._id " +
                    "JOIN _material m ON p._id_material = m._id " +
                    "JOIN _manager_size_color ma ON p._id = ma._id_product " +
                    "WHERE p._id = :_id "+
                    "GROUP BY " +
                    "p._id, p._name, p._desription, " +
                    "t._name, st._name, se._name, p._price, " +
                    "b._name, m._name " +
                    "ORDER BY p._price DESC",
            nativeQuery = true
    )
    Tuple description_product(@Param("_id") String _id);
}
