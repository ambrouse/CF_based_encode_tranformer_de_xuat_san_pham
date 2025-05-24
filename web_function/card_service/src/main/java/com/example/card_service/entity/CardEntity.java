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
@Table(name = "_card")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String _id;

    @Column(name = "_id_user", length = 255)
    private String _id_user;

    @Column(name = "_id_payment_method", length = 255)
    private String _id_payment_method;

    @Column(name = "_id_address", length = 255)
    private String _id_address;

    @Column(name = "_status_card")
    private Byte _status_card;

    @Column(name = "_status_payment")
    private Byte _status_payment;

    @Column(name = "_total_price")
    private Integer _total_price;

    @Column(name = "_day_card")
    private LocalDateTime _day_card;

    @Column(name = "_day_update_status")
    private LocalDateTime _day_update_status;

    @Column(name = "_phone_number", length = 11)
    private String _phone_number;

    @Column(name = "_shipping_fee")
    private Integer _shipping_fee;

    @Column(name = "_day_delete_table")
    private LocalDateTime _day_delete_table;
}
