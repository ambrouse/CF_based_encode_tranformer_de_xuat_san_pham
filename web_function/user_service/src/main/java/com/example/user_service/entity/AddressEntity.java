package com.example.user_service.entity;

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
@Table(name = "_address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String _id;

    @Column(name = "_id_user", length = 255)
    private String _id_user;

    @Column(name = "_name", length = 255)
    private String _name;

    @Column(name = "_status")
    private Boolean _status;

    @Column(name = "_day_delete_tabel")
    private LocalDateTime _day_delete_tabel;
}
