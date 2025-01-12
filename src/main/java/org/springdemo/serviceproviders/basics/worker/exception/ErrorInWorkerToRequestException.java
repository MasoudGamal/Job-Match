package org.springdemo.serviceproviders.basics.worker.exception;

public class ErrorInWorkerToRequestException extends RuntimeException {
    public ErrorInWorkerToRequestException(String message) {
        super(message);
    }
}
