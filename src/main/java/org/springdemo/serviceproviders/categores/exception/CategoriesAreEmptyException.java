package org.springdemo.serviceproviders.categores.exception;

public class CategoriesAreEmptyException extends RuntimeException {
    public CategoriesAreEmptyException(String message) {
        super(message);
    }
}
