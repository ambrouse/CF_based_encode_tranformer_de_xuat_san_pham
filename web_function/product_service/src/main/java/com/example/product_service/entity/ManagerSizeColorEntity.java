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
@Table(name = "_manager_size_color")
public class ManagerSizeColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String id;

    @Column(name = "_id_product", length = 255)
    private String idProduct;

    @Column(name = "_id_size", length = 255)
    private String idSize;

    @Column(name = "_id_color", length = 255)
    private String idColor;

    @Column(name = "_sales")
    private Integer sales;

    @Column(name = "_inventory")
    private Integer inventory;

    @Column(name = "_rate")
    private Short rate;

    @Column(name = "_day_delete_table")
    private LocalDateTime dayDeleteTable;
}
