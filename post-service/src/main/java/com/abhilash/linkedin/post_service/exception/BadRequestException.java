package com.abhilash.linkedin.post_service.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
    }

}
