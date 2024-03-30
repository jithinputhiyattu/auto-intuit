package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.common.exception.*;
import com.dreamblitz.autointuit.domain.entity.CarVariant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CarService extends IService {

    @Autowired
    CarDomainService carDomainService;

    public Mono<CarVariant> getCarById(String id) throws AutoIntuitUnhandledException {
     try {
        return carDomainService.getCarVariantById(id);
     } catch (Exception exception) {
       throw  AutoIntuitUnhandledException.getInstance(exception);
     }
   }

    @Override
    public AutoIntuitException createErrorResponse(Throwable throwable) {
         return new AutoIntuitInvalidInputException(HttpStatus.BAD_REQUEST,
            new AutoIntuitErrorDetails(AutoIntuitErrorCodes.UNKNOWN_ERROR,"Testing error"));
    }
}
