package com.dreamblitz.autointuit.repository;

import com.dreamblitz.autointuit.entity.CarEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository {

    CarEntity getCarById(String id);
}
