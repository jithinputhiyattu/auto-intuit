package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.entity.CarEntity;
import com.dreamblitz.autointuit.repository.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarComparisonService {

   @Autowired
   private ICarRepository iCarRepository;

    public CarEntity getCarById(String id) {
     return null;
    }
}
