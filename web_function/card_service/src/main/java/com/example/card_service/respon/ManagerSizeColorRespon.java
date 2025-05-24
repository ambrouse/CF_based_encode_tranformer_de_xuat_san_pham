package com.example.card_service.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerSizeColorRespon {
    String _id;
    String _name_size;
    String _name_color;
}
