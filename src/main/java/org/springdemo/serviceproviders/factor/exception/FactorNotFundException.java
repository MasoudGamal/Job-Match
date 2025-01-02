package org.springdemo.serviceproviders.factor.exception;

public class FactorNotFundException extends RuntimeException{
    public FactorNotFundException(String message){
        super(message);
    }
}
