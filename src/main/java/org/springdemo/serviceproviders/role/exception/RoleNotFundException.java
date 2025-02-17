package org.springdemo.serviceproviders.role.exception;

public class RoleNotFundException extends RuntimeException {
    public RoleNotFundException(String message) {
        super(message);
    }
}
