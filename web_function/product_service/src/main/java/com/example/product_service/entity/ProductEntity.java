package com.example.product_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "_product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String id;

    @Column(name = "_id_type", length = 255)
    private String idType;

    @Column(name = "_id_brand", length = 255)
    private String idBrand;

    @Column(name = "_id_material", length = 255)
    private String idMaterial;

    @Column(name = "_id_style", length = 255)
    private String idStyle;

    @Column(name = "_id_seasion", length = 255)
    private String idSeasion;

    @Column(name = "_name", length = 255)
    private String name;

    @Column(name = "_price")
    private Integer price;

    @Column(name = "_desription", columnDefinition = "nvarchar(2000)")
    private String description;

    @Column(name = "_day_delete_table")
    private LocalDateTime dayDeleteTable;
}
