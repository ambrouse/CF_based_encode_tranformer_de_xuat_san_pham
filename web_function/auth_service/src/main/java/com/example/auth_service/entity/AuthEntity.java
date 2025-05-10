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
@Table(name = "_authen")
public class AuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id")
    String _id;

    @Column(name = "_id_role")
    String _id_role;

    @Column(name = "_name")
    String _name;

    @Column(name = "_password")
    String _password;

    @Column(name = "_Status_account")
    String _status_account;

    @Column(name = "_day_delete_table")
    LocalDateTime _day_delete_table;


}
