package org.springdemo.jobmatch.order.exception;

public class OrderNotFundException extends RuntimeException {
    public OrderNotFundException(String message) {
        super(message);
    }
}
