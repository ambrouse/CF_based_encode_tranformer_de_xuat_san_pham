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
@Table(name = "_brand")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String id;

    @Column(name = "_name", columnDefinition = "nvarchar(60)")
    private String name;

    @Column(name = "_day_delete_table")
    private LocalDateTime dayDeleteTable;
}
