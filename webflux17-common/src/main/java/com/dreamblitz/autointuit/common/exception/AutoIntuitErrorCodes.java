package com.dreamblitz.autointuit.common.exception;

public class AutoIntuitErrorCodes {

    private AutoIntuitErrorCodes() {
        throw new AssertionError("You are not allowed to create instance of error code class.");
    }

    public static final int UNKNOWN_ERROR = 1000;
    public static final int CODE_BREAK = 1001;
    public static final int NO_SUCH_CAR = 1002;
    public static final int CAR_LIMIT_EXCEED = 1003;
}
