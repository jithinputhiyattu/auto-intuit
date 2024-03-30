package com.dreamblitz.autointuit.infra.repository;

import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import com.dreamblitz.autointuit.infra.util.InfraUtils;
import com.dreamblitz.autointuit.repository.ICarModelRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Getter
public class CarModelRepository implements ICarModelRepository {

    Map<String, CarModelEntity> carModelCollection;

    @Autowired
    InfraUtils infraUtils;

    public CarModelRepository() {
        carModelCollection = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
        String [] carIds = {
                "hyundaiVenue2022",
                "hyundaiCreta2022",
                "tataNexon2022",
                "tataPunch2022",
                "audiA42024"
        };

        /*
        ,
                "hyundaiVenue2023",
                "toyotaFortuner2024",
                "chyundaiCreta2022",
                "carHyundaiCreta2024",
                "mercedesBenzCClass2022",
                "AudiA42023"
         */

        for(String id : carIds) {
            System.out.println("Adding : " +  id);
            CarModelEntity entity = infraUtils.getFromJson("/dto/carModel/" + id +".json", CarModelEntity.class);
            carModelCollection.put(id ,entity);

        }
    }
    @Override
    public Mono<CarModelEntity> getCarById(String id) {
        System.out.println(id);
        CarModelEntity entity = carModelCollection.get(id);
        if(entity != null)  {
            return Mono.just(entity);
        }
        return Mono.empty();
    }

    @Override
    public Flux<CarModelEntity> findAll() {
      return Flux.fromIterable(carModelCollection.values().stream().collect(Collectors.toList()));
    }
}
