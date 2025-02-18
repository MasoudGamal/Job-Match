package org.springdemo.jobmatch.user.exception;

public class MobileNumberIsIncorrect extends RuntimeException {
    public MobileNumberIsIncorrect(String message) {
        super(message);
    }
}
