package org.springdemo.serviceproviders.client.exception;

public class ClientNotFundException extends RuntimeException{
    public ClientNotFundException(String message){
        super(message);
    }
}
