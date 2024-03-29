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
public class CarEntity {
    String brandName;
    String name;
    String id;
    Double price;
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


    public void hideCommon(List<CarEntity> carList) {

       for(CarEntity car : carList){
           
       }

    }
}
