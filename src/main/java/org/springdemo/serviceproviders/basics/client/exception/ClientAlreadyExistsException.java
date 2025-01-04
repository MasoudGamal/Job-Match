package org.springdemo.serviceproviders.basics.client.exception;

public class ClientAlreadyExistsException extends RuntimeException{
    public ClientAlreadyExistsException(String message){
        super(message);
    }
}
