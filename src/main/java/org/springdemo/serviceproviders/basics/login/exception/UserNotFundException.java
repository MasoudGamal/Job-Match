package org.springdemo.serviceproviders.basics.login.exception;

public class UserNotFundException extends RuntimeException{
    public UserNotFundException(String message){
        super(message);
    }
}
