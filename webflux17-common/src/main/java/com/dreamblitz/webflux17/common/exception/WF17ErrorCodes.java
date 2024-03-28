package com.dreamblitz.webflux17.common.exception;

public class WF17ErrorCodes {

    private WF17ErrorCodes() {
        throw new AssertionError("You are not allowed to create instance of error code class.");
    }

    public static final int UNKNOWN_ERROR = 1000;
    public static final int CODE_BREAK = 1001;
}
