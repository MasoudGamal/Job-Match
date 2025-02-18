package org.springdemo.jobmatch.login.exception;

public class UserNotFundException extends RuntimeException{
    public UserNotFundException(String message){
        super(message);
    }
}
