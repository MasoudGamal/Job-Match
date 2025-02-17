package org.springdemo.serviceproviders.uaer.user1.worker.exception;

public class ErrorInWorkerToRequestException extends RuntimeException {
    public ErrorInWorkerToRequestException(String message) {
        super(message);
    }
}
