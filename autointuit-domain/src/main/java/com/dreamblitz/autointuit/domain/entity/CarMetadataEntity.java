package com.dreamblitz.autointuit.domain.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Getter
@Setter
@ToString
@Component
@Scope("prototype")
public class CarMetadataEntity implements Comparable {

    String carId;
    Integer popularity;
    CarSegment segment;
    Integer model;
    String displayName;
    String body;


    public CarMetadataEntity updatePopularity(){
        popularity++;
        return this;
    }

    @Override
    public int compareTo(Object object) {
        CarMetadataEntity that = (CarMetadataEntity) object;
        return (int)(that.popularity-this.popularity);
    }
}
