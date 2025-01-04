package org.springdemo.serviceproviders.job.exception;

public class JobAlreadyExistException extends RuntimeException{
    public JobAlreadyExistException(String message) {
        super(message);
    }
}
