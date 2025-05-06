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
@Table(name =  "_img_product")
public class ImgProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String id;

    @Column(name = "_id_manager_size_color", length = 255)
    private String idManagerSizeColor;

    @Lob
    @Column(name = "_img_byte")
    private byte[] imgByte;

    @Column(name = "_day_delete_table")
    private LocalDateTime dayDeleteTable;
}
