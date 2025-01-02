package org.springdemo.serviceproviders.factor.exception;

public class FactorAlreadyExistsException extends RuntimeException{
    public FactorAlreadyExistsException(String message){
        super(message);
    }
}
