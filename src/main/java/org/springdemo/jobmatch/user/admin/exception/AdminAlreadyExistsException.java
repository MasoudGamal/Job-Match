package org.springdemo.jobmatch.user.admin.exception;

public class AdminAlreadyExistsException extends RuntimeException{
    public AdminAlreadyExistsException(String message){
        super(message);
    }
}
