package org.springdemo.serviceproviders.basics.worker.exception;

public class WorkerNotFundException extends RuntimeException{
    public WorkerNotFundException(String message){
        super(message);
    }
}
