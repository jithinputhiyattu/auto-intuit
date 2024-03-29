package com.dreamblitz.autointuit.repository;

import com.dreamblitz.autointuit.entity.CarEntity;

public interface ICarRepository {

    public CarEntity getCarById(String id);
}
