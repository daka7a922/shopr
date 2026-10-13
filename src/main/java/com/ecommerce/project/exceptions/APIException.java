package com.ecommerce.project.exceptions;

public class APIException extends RuntimeException{

    private static final long versionUID = 1L;

    public APIException(String message) {
        super(message);
    }

    public APIException() {

    }
}
