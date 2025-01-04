package org.springdemo.serviceproviders.basics.admin.exception;

public class AdminNotFundException extends RuntimeException{
    public AdminNotFundException(String message){
        super(message);
    }
}
