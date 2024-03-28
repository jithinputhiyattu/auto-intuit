package com.dreamblitz.webflux17.common.exception;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AutoIntuitErrorDetails {
    private static final long serialVersionUID = 2L;

    private Integer errorCode;
    private String errorMessage;

    public AutoIntuitErrorDetails(Integer errCode, String errMsg) {
        this.errorCode = errCode;
        this.errorMessage = errMsg;
    }
}
