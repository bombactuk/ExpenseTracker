package com.expense.tracker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ServiceClientExceptionHolder {
    INVALID_LOGIN_OR_PASSWORD("CLNT.1000.1", "Incorrect login or password", HttpStatus.UNAUTHORIZED),
    PASSWORD_MISMATCH("CLNT.1000.2", "Passwords do not match", HttpStatus.BAD_REQUEST),
    USER_EXISTS("CLNT.1000.3", "The user already exists", HttpStatus.BAD_REQUEST),

    COULDNT_FETCH_PRODUCT("SRVR.2000.1", "Couldn't fetch product from third-party service", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String errorCode;
    private final String message;
    private final HttpStatus status;

    public ServiceException getException() {
        return new ServiceException(this);
    }

    public ServiceException getExceptionWithMessageParams(String... clientMessage) {
        return new ServiceException(this, clientMessage);
    }
}
