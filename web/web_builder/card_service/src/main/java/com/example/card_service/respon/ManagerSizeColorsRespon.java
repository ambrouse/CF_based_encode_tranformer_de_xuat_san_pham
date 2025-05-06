package com.example.card_service.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerSizeColorsRespon {
    List<ManagerSizeColorRespon> _manager_size_color_respon;
}
