package org.springdemo.serviceproviders.uaer.user1.client.exception;

public class ClientAlreadyExistsException extends RuntimeException{
    public ClientAlreadyExistsException(String message){
        super(message);
    }
}
