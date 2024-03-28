package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.entity.CarEntity;
import com.dreamblitz.autointuit.repository.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarComparisonService {

    @Autowired
    ICarRepository iCarRepository;

    public CarEntity getCarById(String id) {
     return iCarRepository.getCarById(id);
    }
}
