package com.dreamblitz.webflux17.common.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class WF17InvalidInputException extends WF17Exception {

    public WF17InvalidInputException(HttpStatus httpErrorCode, List<WF17ErrorDetails> errors) {
        super(httpErrorCode, errors);
    }

    public WF17InvalidInputException(HttpStatus httpErrorCode, WF17ErrorDetails errors) {
        super(httpErrorCode, errors);
    }
}
