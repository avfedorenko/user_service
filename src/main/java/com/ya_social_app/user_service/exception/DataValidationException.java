package com.ya_social_app.user_service.exception;

import lombok.Getter;

@Getter
public class DataValidationException extends RuntimeException {
    private final String field;

    public DataValidationException(String field, String message) {
        super(message);
        this.field = field;
    }
}
