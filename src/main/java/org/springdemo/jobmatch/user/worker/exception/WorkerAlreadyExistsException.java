package org.springdemo.jobmatch.user.worker.exception;

public class WorkerAlreadyExistsException extends RuntimeException{
    public WorkerAlreadyExistsException(String message){
        super(message);
    }
}
