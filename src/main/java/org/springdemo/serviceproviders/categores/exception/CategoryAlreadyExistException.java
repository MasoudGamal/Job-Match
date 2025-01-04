package org.springdemo.serviceproviders.categores.exception;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException(String message) {
        super(message);
    }
}
