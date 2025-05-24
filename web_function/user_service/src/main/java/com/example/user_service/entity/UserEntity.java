package com.example.user_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String _id;

    @Column(name = "_id_authen", length = 255)
    private String _id_authen;

    @Column(name = "_id_address", length = 255)
    private String _id_address;

    @Column(name = "_name", length = 255)
    private String _name;

    @Column(name = "_age")
    private Byte _age;

    @Column(name = "_time_of_birth")
    private LocalDateTime _time_of_birth;

    @Column(name = "_email", length = 255)
    private String _email;

    @Column(name = "_phone_number", length = 11)
    private String _phone_number;

    @Column(name = "_job", length = 255)
    private String _job;

    @Column(name = "_mean_money_payment")
    private Integer _mean_money_payment;

    @Lob
    @Column(name = "_img_user")
    private byte[] _img_user;

    @Column(name = "_day_delete_table")
    private LocalDateTime _day_delete_table;
}
