package org.springdemo.jobmatch.user.exception;

public class WorkerNotFundException extends RuntimeException{
    public WorkerNotFundException(String message){
        super(message);
    }
}
