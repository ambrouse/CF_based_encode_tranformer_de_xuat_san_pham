package com.example.card_service.api;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiRespon<T> {
    int _request;
    String _request_desription;
    T _result;
}
