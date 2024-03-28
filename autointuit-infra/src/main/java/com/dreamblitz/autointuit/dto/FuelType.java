package com.dreamblitz.autointuit.dto;

public enum FuelType {
    PETROL("Petrol") , DIESEL("Diesel");
    private String fuelType;
    FuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
