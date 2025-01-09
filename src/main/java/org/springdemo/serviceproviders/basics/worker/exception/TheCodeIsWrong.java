package org.springdemo.serviceproviders.basics.worker.exception;

public class TheCodeIsWrong extends RuntimeException {
    public TheCodeIsWrong(String message) {
        super(message);
    }
}
