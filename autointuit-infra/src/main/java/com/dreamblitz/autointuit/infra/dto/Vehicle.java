package com.dreamblitz.autointuit.infra.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {

  String brandName;
  String name;
  Double exShowroomPrice;
  String variantName;
  Float cubicCapacity;
  Float horsePower;
  FuelType fuelType;
  String transmissionType;
}
