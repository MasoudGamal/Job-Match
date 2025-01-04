package org.springdemo.serviceproviders.basics.role.exception;

public class RoleNotFundException extends RuntimeException {
    public RoleNotFundException(String message) {
        super(message);
    }
}
