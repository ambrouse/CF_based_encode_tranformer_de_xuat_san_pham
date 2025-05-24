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
@Table(name = "_search_history")
public class HistorySearchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_id", length = 255)
    private String id;

    @Column(name = "_id_user", length = 255)
    private String idUser;

    @Column(name = "_content", columnDefinition = "nvarchar(255)")
    private String content;

    @Column(name = "_day_search")
    private LocalDateTime daySearch;

    @Column(name = "_day_delete_table")
    private LocalDateTime dayDeleteTable;
}
