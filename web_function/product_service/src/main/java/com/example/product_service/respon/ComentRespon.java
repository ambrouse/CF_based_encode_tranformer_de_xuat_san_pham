package com.example.product_service.respon;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ComentRespon {
    String _id;
    String _coment;
    String _name_user;
    String _img_user;
    String _id_img_coments;
    boolean _check_user;
}
