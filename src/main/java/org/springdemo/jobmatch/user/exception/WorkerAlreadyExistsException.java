package org.springdemo.jobmatch.user.exception;

public class WorkerAlreadyExistsException extends RuntimeException{
    public WorkerAlreadyExistsException(String message){
        super(message);
    }
}
