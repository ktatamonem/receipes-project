package com.mk.backend.assignement.receipes.service.exception;

public class DeleteNullException extends Exception {
    public DeleteNullException() {
        super();
    }

    public DeleteNullException(String message) {
        super(message);
    }

    public DeleteNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteNullException(Throwable cause) {
        super(cause);
    }

    protected DeleteNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
