package com.example.card_service.err_manager;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomException extends  RuntimeException{
    private final String message;
    private final int statusCode;

    public CustomException(String message, int statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

}
