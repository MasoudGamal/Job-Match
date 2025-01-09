package org.springdemo.serviceproviders.otp.entity.exception;

public class OtpNotFundException extends RuntimeException {
    public OtpNotFundException(String message) {
        super(message);
    }
}
