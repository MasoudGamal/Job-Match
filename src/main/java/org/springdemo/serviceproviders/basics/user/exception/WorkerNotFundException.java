package org.springdemo.serviceproviders.basics.user.exception;

public class WorkerNotFundException extends RuntimeException{
    public WorkerNotFundException(String message){
        super(message);
    }
}
