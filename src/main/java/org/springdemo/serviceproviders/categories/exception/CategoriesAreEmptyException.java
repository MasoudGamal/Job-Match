package org.springdemo.serviceproviders.categories.exception;

public class CategoriesAreEmptyException extends RuntimeException {
    public CategoriesAreEmptyException(String message) {
        super(message);
    }
}
