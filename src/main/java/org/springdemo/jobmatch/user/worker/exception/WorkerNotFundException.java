package org.springdemo.jobmatch.user.worker.exception;

public class WorkerNotFundException extends RuntimeException{
    public WorkerNotFundException(String message){
        super(message);
    }
}
