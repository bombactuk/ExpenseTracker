package com.expense.tracker.exception;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        LocalDateTime timestamp,
        String errorCode,
        String message,
        String path
) {
}