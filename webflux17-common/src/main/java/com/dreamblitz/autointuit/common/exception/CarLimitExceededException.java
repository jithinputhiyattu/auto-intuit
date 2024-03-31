package com.dreamblitz.autointuit.common.exception;

import org.springframework.http.HttpStatus;

import static com.dreamblitz.autointuit.common.exception.AutoIntuitErrorCodes.CAR_LIMIT_EXCEED;

public class CarLimitExceededException extends AutoIntuitException {
    public CarLimitExceededException(HttpStatus httpErrorCode, AutoIntuitErrorDetails err) {
        super(httpErrorCode, err);
    }

    public CarLimitExceededException(Integer size) {
        super(HttpStatus.BAD_REQUEST, new AutoIntuitErrorDetails(CAR_LIMIT_EXCEED,"Car limit exceeded : " + size ));
    }
}
