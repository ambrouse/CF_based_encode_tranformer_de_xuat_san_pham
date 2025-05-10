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
@Table(name = "_style")
public class StyleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String id;

    @Column(name = "_name", columnDefinition = "nvarchar(50)")
    private String name;

    @Column(name = "_style_desription", columnDefinition = "nvarchar(max)")
    private String styleDescription;

    @Column(name = "_day_delete_table")
    private LocalDateTime dayDeleteTable;
}
