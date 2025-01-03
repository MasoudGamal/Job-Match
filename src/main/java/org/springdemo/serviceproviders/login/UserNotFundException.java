package org.springdemo.serviceproviders.login;

public class UserNotFundException extends RuntimeException{
    public UserNotFundException(String message){
        super(message);
    }
}
