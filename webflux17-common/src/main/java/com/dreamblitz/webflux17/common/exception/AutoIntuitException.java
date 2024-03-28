package com.dreamblitz.webflux17.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AutoIntuitException extends Exception {

    private final HttpStatus httpErrorCode;

    private final List<AutoIntuitErrorDetails> errors;

    public AutoIntuitException(HttpStatus httpErrorCode, AutoIntuitErrorDetails err) {
        this.httpErrorCode = httpErrorCode;
        this.errors = new ArrayList<>();
        this.errors.add(err);
        
    }

    public AutoIntuitException(HttpStatus httpErrorCode, List<AutoIntuitErrorDetails> err) {
        this.httpErrorCode = httpErrorCode;
        this.errors = new ArrayList<>();
        this.errors.addAll(err);
    }

    public void addError(List<AutoIntuitErrorDetails> err) {
        this.errors.addAll(err);
    }

}
