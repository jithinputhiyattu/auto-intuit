package com.dreamblitz.autointuit.repository;

import com.dreamblitz.autointuit.entity.CarEntity;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Getter
public class CarRepository implements ICarRepository {

    @Override
    public CarEntity getCarById(String id) {
        return null;
    }
}
