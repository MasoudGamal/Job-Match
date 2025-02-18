package org.springdemo.jobmatch.order.exception;

public class ErrorInTheCaseException extends RuntimeException {
    public ErrorInTheCaseException(String message) {
        super(message);
    }
}
