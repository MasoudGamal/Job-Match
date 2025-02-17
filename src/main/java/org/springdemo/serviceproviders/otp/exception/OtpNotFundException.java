package org.springdemo.serviceproviders.otp.exception;

public class OtpNotFundException extends RuntimeException {
    public OtpNotFundException(String message) {
        super(message);
    }
}
