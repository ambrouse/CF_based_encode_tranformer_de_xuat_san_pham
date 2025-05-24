package com.example.auth_service.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id")
    private String _id;

    @Column(name = "_id_authen")
    private String _id_authen;

    @Column(name = "_id_address")
    private String _id_address;

    @Column(name = "_name")
    private String _name;

    @Column(name = "_age")
    private Byte _age;

    @Column(name = "_time_of_birth")
    private LocalDateTime _time_of_birth;

    @Column(name = "_email")
    private String _email;

    @Column(name = "_phone_number")
    private String _phone_number;

    @Column(name = "_job")
    private String _job;

    @Column(name = "_mean_money_payment")
    private Integer _mean_money_payment;

    @Column(name = "_day_delete_table")
    private LocalDateTime _day_delete_table;
}
