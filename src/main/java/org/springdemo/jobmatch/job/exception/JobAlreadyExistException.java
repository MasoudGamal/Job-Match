package org.springdemo.jobmatch.job.exception;

public class JobAlreadyExistException extends RuntimeException{
    public JobAlreadyExistException(String message) {
        super(message);
    }
}
