package com.dreamblitz.autointuit.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@NoArgsConstructor
@Scope("prototype")
public class CarVariant {
    String brandName;
    String name;
    String id;
    Double exShowroomPrice;
    Double insurance;
    String variantName;
    Float cubicCapacity;
    Float horsePower;
    FuelType fuelType;
    String transmissionType;
    BodyType bodyType;
    int numDoors;
    int seatingCapacity;
    int numberOfAirbags;
    Boolean automaticClimateControl;
}
