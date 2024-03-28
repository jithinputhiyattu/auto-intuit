package com.dreamblitz.webflux17.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class WF17Exception extends Exception {

    private final HttpStatus httpErrorCode;

    private final List<WF17ErrorDetails> errors;

    public WF17Exception(HttpStatus httpErrorCode, WF17ErrorDetails err) {
        this.httpErrorCode = httpErrorCode;
        this.errors = new ArrayList<>();
        this.errors.add(err);
        
    }

    public WF17Exception(HttpStatus httpErrorCode, List<WF17ErrorDetails> err) {
        this.httpErrorCode = httpErrorCode;
        this.errors = new ArrayList<>();
        this.errors.addAll(err);
    }

    public void addError(List<WF17ErrorDetails> err) {
        this.errors.addAll(err);
    }

}
