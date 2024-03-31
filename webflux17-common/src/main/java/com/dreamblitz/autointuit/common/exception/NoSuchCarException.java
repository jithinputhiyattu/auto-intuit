package com.dreamblitz.autointuit.common.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class NoSuchCarException extends AutoIntuitException {

    public NoSuchCarException(HttpStatus httpErrorCode, List<AutoIntuitErrorDetails> errors) {
        super(httpErrorCode, errors);
    }

    public NoSuchCarException(HttpStatus httpErrorCode, AutoIntuitErrorDetails errors) {
        super(httpErrorCode, errors);
    }

    public NoSuchCarException( String carId) {
        super(HttpStatus.NOT_FOUND,
                new AutoIntuitErrorDetails(AutoIntuitErrorCodes.NO_SUCH_CAR,
                        "No such car found in the system : " + carId));
    }
    public NoSuchCarException( String[] carIds) {
        super(HttpStatus.NOT_FOUND,
                new AutoIntuitErrorDetails(AutoIntuitErrorCodes.NO_SUCH_CAR,
                        "No such car found in the system : " + carIds.toString()));
    }
}
