package com.solvd.onlineshop.exception;

public class InvalidDataBaseConnection extends RuntimeException {
    public InvalidDataBaseConnection() {
    }

    public InvalidDataBaseConnection(String message) {
        super(message);
    }

    public InvalidDataBaseConnection(Throwable cause) {
        super(cause);
    }

    public InvalidDataBaseConnection(String message, Throwable cause) {
        super(message, cause);
    }
}