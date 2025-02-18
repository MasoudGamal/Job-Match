package org.springdemo.jobmatch.user.worker.exception;

public class ErrorInWorkerToRequestException extends RuntimeException {
    public ErrorInWorkerToRequestException(String message) {
        super(message);
    }
}
