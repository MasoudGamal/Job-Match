package org.springdemo.serviceproviders.uaer.user1.admin.exception;

public class AdminAlreadyExistsException extends RuntimeException{
    public AdminAlreadyExistsException(String message){
        super(message);
    }
}
