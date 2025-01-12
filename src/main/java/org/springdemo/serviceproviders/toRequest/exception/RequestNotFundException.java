package org.springdemo.serviceproviders.toRequest.exception;

public class RequestNotFundException extends RuntimeException {
    public RequestNotFundException(String message) {
        super(message);
    }
}
