package org.springdemo.serviceproviders.toRequest.exception;

public class RequestsAreEmptyException extends RuntimeException {
    public RequestsAreEmptyException(String message) {
        super(message);
    }
}
