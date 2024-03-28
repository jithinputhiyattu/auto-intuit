package com.dreamblitz.autointuit.common.exception;

import org.springframework.http.HttpStatus;

public class AutoIntuitUnhandledException extends AutoIntuitException {
    public AutoIntuitUnhandledException(HttpStatus httpErrorCode, AutoIntuitErrorDetails err) {
        super(httpErrorCode, err);
    }

    public static AutoIntuitUnhandledException getInstance(Throwable throwable) {
        return new AutoIntuitUnhandledException(HttpStatus.INTERNAL_SERVER_ERROR,
            new AutoIntuitErrorDetails(AutoIntuitErrorCodes.CODE_BREAK,throwable.getMessage()));
    }
}
