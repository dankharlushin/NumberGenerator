package com.example.gaishnik.exception;

public class NoFreePlatesException extends Exception {

    public NoFreePlatesException() {
        super();
    }

    public NoFreePlatesException(String message) {
        super(message);
    }

    public NoFreePlatesException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFreePlatesException(Throwable cause) {
        super(cause);
    }

    protected NoFreePlatesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
