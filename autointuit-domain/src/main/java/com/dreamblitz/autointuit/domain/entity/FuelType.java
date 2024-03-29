package com.dreamblitz.autointuit.domain.entity;

public enum FuelType {
    PETROL("Petrol") , DIESEL("Diesel");
    private String fuelType;
    FuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
