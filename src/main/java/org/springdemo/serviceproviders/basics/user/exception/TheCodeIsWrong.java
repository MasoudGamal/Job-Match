package org.springdemo.serviceproviders.basics.user.exception;

public class TheCodeIsWrong extends RuntimeException {
    public TheCodeIsWrong(String message) {
        super(message);
    }
}
