package org.springdemo.serviceproviders.login.exception;

public class AccountIsNotActiveException extends RuntimeException {
    public AccountIsNotActiveException(String message) {
        super(message);
    }
}
