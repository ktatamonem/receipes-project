package com.mk.backend.assignement.receipes.service.dto.mapper.exception;

public class MapperNullObjectException extends RuntimeException{
    public MapperNullObjectException() {
    }

    public MapperNullObjectException(String message) {
        super(message);
    }

    public MapperNullObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapperNullObjectException(Throwable cause) {
        super(cause);
    }

    public MapperNullObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
