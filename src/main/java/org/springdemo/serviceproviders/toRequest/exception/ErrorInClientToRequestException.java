package org.springdemo.serviceproviders.toRequest.exception;

public class ErrorInClientToRequestException extends RuntimeException {
    public ErrorInClientToRequestException(String message) {
        super(message);
    }
}
