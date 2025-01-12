package org.springdemo.serviceproviders.toRequest.exception;

public class ErrorInTheCaseException extends RuntimeException {
    public ErrorInTheCaseException(String message) {
        super(message);
    }
}
