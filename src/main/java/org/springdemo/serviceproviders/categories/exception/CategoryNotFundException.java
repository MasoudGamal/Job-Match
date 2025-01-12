package org.springdemo.serviceproviders.categories.exception;

public class CategoryNotFundException extends RuntimeException {
    public CategoryNotFundException(String message) {
        super(message);
    }
}
