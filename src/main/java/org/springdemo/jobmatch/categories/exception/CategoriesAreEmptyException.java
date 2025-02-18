package org.springdemo.jobmatch.categories.exception;

public class CategoriesAreEmptyException extends RuntimeException {
    public CategoriesAreEmptyException(String message) {
        super(message);
    }
}
