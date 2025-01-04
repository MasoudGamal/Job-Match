package org.springdemo.serviceproviders.basics.worker.exception;

public class WorkerAlreadyExistsException extends RuntimeException{
    public WorkerAlreadyExistsException(String message){
        super(message);
    }
}
