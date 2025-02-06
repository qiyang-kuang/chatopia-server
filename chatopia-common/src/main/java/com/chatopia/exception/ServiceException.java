package com.chatopia.exception;

public class ServiceException extends RuntimeException {
    public static final int DEFAULT_CODE = 500;
    private final int code;

    public ServiceException(String message) {
        super(message);
        this.code = DEFAULT_CODE;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }
}
