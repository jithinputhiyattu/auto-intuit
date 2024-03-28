package com.dreamblitz.autointuit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {

  String brandName;
  String name;
  Double price;
  String variantName;
  Float cubicCapacity;
  Float horsePower;
  FuelType fuelType;
  String transmissionType;
}
