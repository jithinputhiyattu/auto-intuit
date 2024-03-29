package com.dreamblitz.autointuit.adapter.controller;

import com.dreamblitz.autointuit.common.exception.AutoIntuitErrorDetails;
import com.dreamblitz.autointuit.common.exception.AutoIntuitInvalidInputException;
import com.dreamblitz.autointuit.common.exception.AutoIntuitUnhandledException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class GenericExceptionHandler {


    @ExceptionHandler(value = {AutoIntuitInvalidInputException.class})
    public ResponseEntity< List<AutoIntuitErrorDetails>> handleWF17InvalidInputException(
        AutoIntuitInvalidInputException wf17InvalidInputException) {
        // TO DO Modify this method
        List<AutoIntuitErrorDetails> wf17ErrorDetails = wf17InvalidInputException.getErrors();
        return new ResponseEntity<>(wf17ErrorDetails,
            wf17InvalidInputException.getHttpErrorCode());
    }


    @ExceptionHandler(value = {AutoIntuitUnhandledException.class})
    public ResponseEntity< List<AutoIntuitErrorDetails>> handleWF17UnhandledException(
            AutoIntuitUnhandledException wf17UnhandledException) {
        // TO DO Modify this method
        List<AutoIntuitErrorDetails> wf17ErrorDetails = wf17UnhandledException.getErrors();
        return new ResponseEntity<>(wf17ErrorDetails,
                wf17UnhandledException.getHttpErrorCode());
    }
}
