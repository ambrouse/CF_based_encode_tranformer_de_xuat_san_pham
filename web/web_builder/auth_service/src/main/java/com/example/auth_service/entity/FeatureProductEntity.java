package com.example.auth_service.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "_feature_product")
public class FeatureProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String id;

    @Column(name = "_id_manager_size_color", length = 255)
    private String idManagerSizeColor;

    @Column(name = "_id_user", length = 255)
    private String idUser;

    @Column(name = "_count_clich")
    private Integer countClich;

    @Column(name = "_count_pay_product")
    private Integer countPayProduct;

    @Column(name = "_month_pay_")
    private Integer monthPay;

    @Column(name = "_last_purchase")
    private Integer lastPurchase;

    @Column(name = "_count_cancel")
    private Integer countCancel;

    @Column(name = "_count_return")
    private Integer countReturn;

    @Column(name = "_count_add_card")
    private Integer countAddCard;

    @Column(name = "_discount")
    private Boolean discount;

    @Column(name = "_day_delete_table")
    private LocalDateTime dayDeleteTable;
}
