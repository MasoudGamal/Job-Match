package org.springdemo.serviceproviders.order.exception;

public class OrderNotFundException extends RuntimeException {
    public OrderNotFundException(String message) {
        super(message);
    }
}
