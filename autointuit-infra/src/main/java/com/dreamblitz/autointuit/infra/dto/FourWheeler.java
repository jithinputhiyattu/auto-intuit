package com.dreamblitz.autointuit.infra.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FourWheeler extends Vehicle {
    BodyType bodyType;
    int numDoors;
    int numberOfAirbags;
}
