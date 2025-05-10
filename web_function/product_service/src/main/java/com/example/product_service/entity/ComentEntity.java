package com.example.product_service.entity;

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
@Table(name = "_coment")
public class ComentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", nullable = false, length = 255)
    private String _id;

    @Column(name = "_id_user", length = 255)
    private String _id_user;

    @Column(name = "_id_manager_size_color", length = 255)
    private String _id_manager_size_color;

    @Column(name = "_content", columnDefinition = "nvarchar(max)")
    private String _content;

    @Column(name = "_day_coment")
    private LocalDateTime _day_coment;

    @Column(name = "_day_delete_table")
    private LocalDateTime _day_delete_table;
}
