package com.dreamblitz.autointuit.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@ToString
@Component
@NoArgsConstructor
@Scope("prototype")
public class CarVariant implements Hideable {
    String brandName;
    String name;
    String id;
    String carModelId;
    Double exShowroomPrice;
    Double insurance;
    String variantName;
    FuelType fuelType;
    String transmissionType;
    BodyType bodyType;
    Integer numDoors;
    Integer seatingCapacity;
    Integer numberOfAirbags;
    Boolean automaticClimateControl;
    Dimensions dimensions;
    Engine engine;


}
