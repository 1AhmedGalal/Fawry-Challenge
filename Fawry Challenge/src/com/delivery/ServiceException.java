package com.delivery;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
