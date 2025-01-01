package org.springdemo.serviceproviders.user;

public class RoleNotFundException extends RuntimeException {
    public RoleNotFundException(String message) {
        super(message);
    }
}
