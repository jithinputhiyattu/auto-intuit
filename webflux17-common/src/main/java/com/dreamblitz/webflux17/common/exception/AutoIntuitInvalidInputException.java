package com.dreamblitz.webflux17.common.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class AutoIntuitInvalidInputException extends AutoIntuitException {

    public AutoIntuitInvalidInputException(HttpStatus httpErrorCode, List<AutoIntuitErrorDetails> errors) {
        super(httpErrorCode, errors);
    }

    public AutoIntuitInvalidInputException(HttpStatus httpErrorCode, AutoIntuitErrorDetails errors) {
        super(httpErrorCode, errors);
    }
}
