package com.dreamblitz.webflux17.common.exception;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WF17ErrorDetails {
    private static final long serialVersionUID = 2L;

    private Integer errorCode;
    private String errorMessage;

    public WF17ErrorDetails(Integer errCode, String errMsg) {
        this.errorCode = errCode;
        this.errorMessage = errMsg;
    }
}
