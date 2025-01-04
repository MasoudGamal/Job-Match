package org.springdemo.serviceproviders.basics.client.exception;

public class ClientNotFundException extends RuntimeException{
    public ClientNotFundException(String message){
        super(message);
    }
}
