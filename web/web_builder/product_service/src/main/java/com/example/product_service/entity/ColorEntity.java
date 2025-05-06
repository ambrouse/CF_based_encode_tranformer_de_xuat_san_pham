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
@Table(name = "_color")
public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String id;

    @Column(name = "_name", columnDefinition = "nvarchar(50)")
    private String name;

    @Column(name = "_day_delete_table")
    private LocalDateTime dayDeleteTable;
}
