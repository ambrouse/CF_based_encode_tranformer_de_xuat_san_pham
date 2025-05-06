package com.example.product_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "_seasion")
public class SeasionEntity {
    @Id
    @Column(name = "_id", length = 255)
    private String id;

    @Column(name = "_name", columnDefinition = "nvarchar(20)")
    private String name;

    @Column(name = "day_delete_table")
    private LocalDateTime dayDeleteTable;
}
