package com.dreamblitz.autointuit.domain.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Component
@Scope("prototype")
public class CarModelEntity implements Comparable {
    String brandName;
    String name;
    String id;
    List<String> variants;

    /*
    The following fields will be used for calculating suggestion score
    */
    Double basePrize;
    Double maxPrize;
    String body;
    Integer seatingCapacity;

    // This score will be used for suggestion
    double score;

    public CarModelEntity() {
        variants = new ArrayList<>();
    }

    public CarModelEntity createScore(CarModelEntity carModel) {
        Double basePrizeDifferance = Math.abs(this.basePrize - carModel.basePrize) +1;
        Double maxPrizeDifferance = Math.abs(this.maxPrize - carModel.maxPrize) + 1;
        this.score = 10000000 / basePrizeDifferance  + 10000000/maxPrizeDifferance;
        if(this.body.equalsIgnoreCase(carModel.body)) {
            this.score += 100; // This should be in Cloud configuration
        }
        if(this.seatingCapacity == carModel.seatingCapacity) {
            this.score += 100; // This should be in Cloud configuration
        }
        return this;
    }

    @Override
    public int compareTo(Object object) {
        CarModelEntity that = (CarModelEntity) object;
        return (int)(that.score-this.score);
    }
}
