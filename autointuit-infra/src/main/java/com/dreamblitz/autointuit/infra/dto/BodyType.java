package com.dreamblitz.autointuit.infra.dto;

public enum BodyType {

    OPEN("Open") , CLOSED("Closed");
    private String bodyType;
    BodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
