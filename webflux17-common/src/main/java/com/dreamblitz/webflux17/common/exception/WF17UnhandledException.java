package com.dreamblitz.webflux17.common.exception;

import org.springframework.http.HttpStatus;

public class WF17UnhandledException extends WF17Exception {
    public WF17UnhandledException(HttpStatus httpErrorCode, WF17ErrorDetails err) {
        super(httpErrorCode, err);
    }

    public static WF17UnhandledException getInstance(Throwable throwable) {
        return new WF17UnhandledException(HttpStatus.INTERNAL_SERVER_ERROR,
            new WF17ErrorDetails(WF17ErrorCodes.CODE_BREAK,throwable.getMessage()));
    }
}
