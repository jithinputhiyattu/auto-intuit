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
public class CarEntity {
    String brandName;
    String name;
    Double price;
    String variantName;
    Float cubicCapacity;
    Float horsePower;
    FuelType fuelType;
    String transmissionType;
    BodyType bodyType;
    int numDoors;
    int numberOfAirbags;
}
