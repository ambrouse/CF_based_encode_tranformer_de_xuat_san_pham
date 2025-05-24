package com.example.auth_service.err_manager;

import com.example.auth_service.api.ApiRespon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ApiRespon<String>> valid(MethodArgumentNotValidException customException){

        return ResponseEntity.badRequest().body(ApiRespon.<String>builder()
                ._request(200)
                ._request_desription("Request error")
                ._result(customException.getFieldError().getDefaultMessage())
                .build());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiRespon<String>> handleCustomException(CustomException ex) {
        return ResponseEntity.badRequest().body(ApiRespon.<String>builder()
                ._result(ex.getMessage())
                ._request(200)
                ._request_desription("Request error")
                .build());
    }


}
