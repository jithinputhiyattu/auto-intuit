package com.dreamblitz.autointuit.infra.repository;

import com.dreamblitz.autointuit.domain.entity.CarEntity;
import com.dreamblitz.autointuit.infra.util.InfraUtils;
import com.dreamblitz.autointuit.repository.ICarRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Getter
public class CarRepository implements ICarRepository {

    Map<String, CarEntity> carCollection;

    @Autowired
    InfraUtils infraUtils;

    public CarRepository() {
      carCollection = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
        String [] carIds = {
                "carHyundaiVenueSPlusPetrol2022",
                "carToyotaFortuner2022",
                "carHyundaiCretaSPetrol2022",
                "carHyundaiCretaSDiesel2022",
                "carHyundaiVenueSPlusDiesel2022",
                "carHyundaiVenueSXDiesel2022"
        };

        for(String id : carIds) {
            System.out.println("Putting : " +  id);
            CarEntity entity = infraUtils.getFromJson("/dto/" + id +".json", CarEntity.class);

            carCollection.put(id ,entity);

        }
    }

    @Override
    public Mono<CarEntity> getCarById(String id) {

        System.out.println(id);
        CarEntity entity = carCollection.get(id);
        if(entity != null)  {
            return Mono.just(entity);
        }
        return Mono.empty();
    }

    @Override
    public Flux<CarEntity> getCarsById(String[] vehicleId) {

        List<CarEntity> list = new ArrayList<>();
        for(String id : vehicleId) {
            CarEntity entity = carCollection.get(id);
            if(entity!=null) {
                list.add(entity);
            }
        }
        return Flux.fromIterable(list);
    }
}
