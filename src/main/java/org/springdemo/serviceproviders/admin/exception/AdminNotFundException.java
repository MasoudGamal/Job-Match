package org.springdemo.serviceproviders.admin.exception;

public class AdminNotFundException extends RuntimeException{
    public AdminNotFundException(String message){
        super(message);
    }
}
