package com.example.card_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "_feature_product")
public class FeatureProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String _id;

    @Column(name = "_id_manager_size_color", length = 255)
    private String _id_manager_size_color;

    @Column(name = "_id_user", length = 255)
    private String _id_user;

    @Column(name = "_count_clich")
    private Integer _count_clich;

    @Column(name = "_count_pay_product")
    private Integer _count_pay_product;

    @Column(name = "_month_pay_")
    private Byte _month_pay_;

    @Column(name = "_last_purchase")
    private Integer _last_purchase;

    @Column(name = "_count_cancel")
    private Integer _count_cancel;

    @Column(name = "_count_return")
    private Integer _count_return;

    @Column(name = "_count_add_card")
    private Integer _count_add_card;

    @Column(name = "_discount")
    private Boolean _discount;

    @Column(name = "_day_delete_table")
    private LocalDateTime _day_delete_table;
}
