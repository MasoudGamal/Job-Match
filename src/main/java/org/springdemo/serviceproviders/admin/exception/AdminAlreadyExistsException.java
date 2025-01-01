package org.springdemo.serviceproviders.admin.exception;

public class AdminAlreadyExistsException extends RuntimeException{
    public AdminAlreadyExistsException(String message){
        super(message);
    }
}
