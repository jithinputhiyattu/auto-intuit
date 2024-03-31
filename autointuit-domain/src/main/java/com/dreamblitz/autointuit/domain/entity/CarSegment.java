package com.dreamblitz.autointuit.domain.entity;

public enum CarSegment {
    Economy("Economy"),MiddleClass("MiddleClass"),TopClass("TopClass"), Premium("Premium"), Luxury("Luxury");
    private String carSegment;
    CarSegment(String carSegment) {
        this.carSegment = carSegment;
    }
}
