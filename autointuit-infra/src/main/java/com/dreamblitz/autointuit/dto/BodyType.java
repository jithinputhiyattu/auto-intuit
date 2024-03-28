package com.dreamblitz.autointuit.dto;

public enum BodyType {

    OPEN("Open") , CLOSED("Closed");
    private String bodyType;
    BodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
