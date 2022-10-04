package com.solvd.onlineshop.exception;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException() {
    }
    public InvalidDateException(String message) {
        super(message);
    }

    public InvalidDateException(Throwable cause) {
        super(cause);
    }

    public InvalidDateException(String message, Throwable cause) {
        super(message, cause);
    }
}