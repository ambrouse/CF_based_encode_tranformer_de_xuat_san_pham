package com.example.product_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "_discount_product")
public class DiscountProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String id;

    @Column(name = "_id_manager_size_color", length = 255)
    private String idManagerSizeColor;

    @Column(name = "_discount_percent")
    private Short discountPercent;

    @Column(name = "_day_delete_table")
    private LocalDateTime dayDeleteTable;
}
