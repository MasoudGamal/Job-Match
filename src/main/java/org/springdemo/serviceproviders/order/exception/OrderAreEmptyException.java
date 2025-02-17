package org.springdemo.serviceproviders.order.exception;

public class OrderAreEmptyException extends RuntimeException {
    public OrderAreEmptyException(String message) {
        super(message);
    }
}
