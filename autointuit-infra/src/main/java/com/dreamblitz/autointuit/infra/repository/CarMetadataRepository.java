package com.dreamblitz.autointuit.infra.repository;

import com.dreamblitz.autointuit.domain.entity.CarMetadataEntity;
import com.dreamblitz.autointuit.domain.entity.CarModelEntity;
import com.dreamblitz.autointuit.infra.util.InfraUtils;
import com.dreamblitz.autointuit.repository.ICarMetadataRepository;
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
public class CarMetadataRepository implements ICarMetadataRepository {


    Map<String, CarMetadataEntity> carMetaCollection;

    @Autowired
    InfraUtils infraUtils;

    public CarMetadataRepository() {
        carMetaCollection = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
        String [] carIds = {
                "hyundaiVenue2022",
                "hyundaiCreta2022",
                "tataNexon2022",
                "tataPunch2022",
                "marutiBrezza2024"
        };

        for(String id : carIds) {
            System.out.println("Adding : " +  id);
            CarMetadataEntity entity = infraUtils.getFromJson("/dto/carMetadata/" + id +".json", CarMetadataEntity.class);
            carMetaCollection.put(id ,entity);

        }
    }

    @Override
    public Mono<CarMetadataEntity> getCarById(String id) {
        System.out.println(id);
        CarMetadataEntity entity = carMetaCollection.get(id);
        if(entity != null)  {
            return Mono.just(entity);
        }
        return Mono.empty();
    }

    @Override
    public Flux<CarMetadataEntity> findAll() {
        return Flux.fromIterable(carMetaCollection.values().stream().collect(Collectors.toList()));
    }
}
