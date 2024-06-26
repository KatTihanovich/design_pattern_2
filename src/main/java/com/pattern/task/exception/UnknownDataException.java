package com.pattern.task.exception;

public class UnknownDataException extends Exception {
    public UnknownDataException(String message) {
        super(message);
    }

    public UnknownDataException(String message, Throwable cause) {
        super(message, cause);
    }
}