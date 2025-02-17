package org.springdemo.serviceproviders.uaer.user1.worker.exception;

public class WorkerAlreadyExistsException extends RuntimeException{
    public WorkerAlreadyExistsException(String message){
        super(message);
    }
}
