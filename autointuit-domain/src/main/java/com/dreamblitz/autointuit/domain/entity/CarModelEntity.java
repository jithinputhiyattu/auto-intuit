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


    @Override
    public int compareTo(Object object) {
        CarModelEntity that = (CarModelEntity) object;
        return (int)(that.score-this.score);
    }
}
