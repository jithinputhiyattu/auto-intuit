package com.dreamblitz.autointuit.infra.repository;

import com.dreamblitz.autointuit.domain.entity.CarVariant;
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
public class CarVariantRepository implements ICarRepository {

    Map<String, CarVariant> carCollection;

    @Autowired
    InfraUtils infraUtils;

    public CarVariantRepository() {
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
            CarVariant entity = infraUtils.getFromJson("/dto/carVariant/" + id +".json", CarVariant.class);

            carCollection.put(id ,entity);

        }
    }

    @Override
    public Mono<CarVariant> getCarById(String id) {
        this.init();

        System.out.println(id);
        CarVariant entity = carCollection.get(id);
        if(entity != null)  {
            return Mono.just(entity);
        }
        return Mono.empty();
    }

    @Override
    public Flux<CarVariant> getCarsById(String[] vehicleId) {
        this.init();
        List<CarVariant> list = new ArrayList<>();
        for(String id : vehicleId) {
            CarVariant entity = carCollection.get(id);
            if(entity!=null) {
                list.add(entity);
            }
        }
        return Flux.fromIterable(list);
    }
}
