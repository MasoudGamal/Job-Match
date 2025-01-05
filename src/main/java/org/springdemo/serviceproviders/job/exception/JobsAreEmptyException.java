package org.springdemo.serviceproviders.job.exception;

public class JobsAreEmptyException extends RuntimeException {
    public JobsAreEmptyException(String message) {
        super(message);
    }
}
