package org.springdemo.jobmatch.categories.exception;

public class CategoryNotFundException extends RuntimeException {
    public CategoryNotFundException(String message) {
        super(message);
    }
}
