package org.springdemo.jobmatch.user.client.exception;

public class ClientNotFundException extends RuntimeException{
    public ClientNotFundException(String message){
        super(message);
    }
}
