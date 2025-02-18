package org.springdemo.jobmatch.user.worker.exception;

public class MobileNumberIsIncorrect extends RuntimeException {
    public MobileNumberIsIncorrect(String message) {
        super(message);
    }
}
