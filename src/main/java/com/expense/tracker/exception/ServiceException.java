package com.expense.tracker.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {
    private final ServiceClientExceptionHolder details;
    private String[] clientMessages;

    public ServiceException(ServiceClientExceptionHolder details) {
        this.details = details;
    }

    public ServiceException(ServiceClientExceptionHolder details, String... clientMessages) {
        this.details = details;
        this.clientMessages = clientMessages;
    }

    public String getClientMessage() {
        if (clientMessages != null) {
            return String.format(details.getMessage(), (Object[]) clientMessages);
        }
        return details.getMessage();
    }

    public String getErrorCode() {
        return details.getErrorCode();
    }

    public HttpStatus getStatus() {
        return details.getStatus();
    }
}
