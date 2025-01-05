package org.springdemo.serviceproviders.job.exception;

public class JobNotFundException extends RuntimeException {
    public JobNotFundException(String message) {
        super(message);
    }
}
