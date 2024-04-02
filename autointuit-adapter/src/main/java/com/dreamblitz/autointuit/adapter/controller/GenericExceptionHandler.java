package com.dreamblitz.autointuit.adapter.controller;

import com.dreamblitz.autointuit.common.exception.AutoIntuitErrorDetails;
import com.dreamblitz.autointuit.common.exception.NoSuchCarException;
import com.dreamblitz.autointuit.common.exception.CarLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(value = {NoSuchCarException.class})
    public ResponseEntity< List<AutoIntuitErrorDetails>> handleNoSuchCarException(
        NoSuchCarException noSuchCarException) {
        List<AutoIntuitErrorDetails> errorDetails = noSuchCarException.getErrors();
        return new ResponseEntity<>(errorDetails, noSuchCarException.getHttpErrorCode());
    }

    @ExceptionHandler(value = {CarLimitExceededException.class})
    public ResponseEntity< List<AutoIntuitErrorDetails>> handleCarLimitExceededException(
            CarLimitExceededException carLimitExceededException) {
        List<AutoIntuitErrorDetails> errorDetails = carLimitExceededException.getErrors();
        return new ResponseEntity<>(errorDetails, carLimitExceededException.getHttpErrorCode());
    }
}
