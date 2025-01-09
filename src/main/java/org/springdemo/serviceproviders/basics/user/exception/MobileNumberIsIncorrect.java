package org.springdemo.serviceproviders.basics.user.exception;

public class MobileNumberIsIncorrect extends RuntimeException {
    public MobileNumberIsIncorrect(String message) {
        super(message);
    }
}
