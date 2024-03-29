package com.dreamblitz.autointuit.domain.entity;

public enum BodyType {

    OPEN("Open") , CLOSED("Closed");
    private String bodyType;
    BodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
