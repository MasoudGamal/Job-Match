package org.springdemo.serviceproviders.basics.worker.exception;

public class MobileNumberIsIncorrect extends RuntimeException {
    public MobileNumberIsIncorrect(String message) {
        super(message);
    }
}
