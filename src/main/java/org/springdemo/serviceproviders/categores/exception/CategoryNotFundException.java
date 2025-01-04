package org.springdemo.serviceproviders.categores.exception;

public class CategoryNotFundException extends RuntimeException {
    public CategoryNotFundException(String message) {
        super(message);
    }
}
