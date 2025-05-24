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
@Table(name = "_img_coment")
public class ImgComentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String id;

    @Column(name = "_id_coment", length = 255)
    private String _id_coment;

    @Lob
    @Column(name = "_img_byte")
    private byte[] _img_byte;

    @Column(name = "_day_delete_table")
    private LocalDateTime _day_delete_table;
}
