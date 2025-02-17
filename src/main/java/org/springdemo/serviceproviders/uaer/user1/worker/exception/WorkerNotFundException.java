package org.springdemo.serviceproviders.uaer.user1.worker.exception;

public class WorkerNotFundException extends RuntimeException{
    public WorkerNotFundException(String message){
        super(message);
    }
}
