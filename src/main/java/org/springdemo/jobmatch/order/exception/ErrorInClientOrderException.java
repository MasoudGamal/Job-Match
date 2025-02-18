package org.springdemo.jobmatch.order.exception;

public class ErrorInClientOrderException extends RuntimeException {
    public ErrorInClientOrderException(String message) {
        super(message);
    }
}
