package com.example.card_service.entity;

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
@Table(name = "_card_mini")
public class CardMiniEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String _id;

    @Column(name = "_id_card", length = 255)
    private String _id_card;

    @Column(name = "_id_manager_size_color", length = 255)
    private String _id_manager_size_color;

    @Column(name = "_price")
    private Integer _price;

    @Column(name = "_count")
    private Integer _count;

    @Column(name = "_day_delete_table")
    private LocalDateTime _day_delete_table;
}
