package org.springdemo.serviceproviders.basics.login.exception;

public class PasswordIncorrectException extends RuntimeException {
    public PasswordIncorrectException(String message) {
        super(message);
    }
}
