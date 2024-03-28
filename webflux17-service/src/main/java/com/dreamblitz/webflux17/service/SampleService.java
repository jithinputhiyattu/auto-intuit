package com.dreamblitz.webflux17.service;

import com.dreamblitz.webflux17.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SampleService extends IService {

    public Mono<String> sampleResponse() throws WF17UnhandledException {
     try {
        String x = null;
        if(x.toString()=="y") {
            /// DO nothing
        }
        return errorHandler(new Throwable("error 0"));
       // return  Mono.just("Jithin ....");

     } catch (Exception exception) {
       throw  WF17UnhandledException.getInstance(exception);
     }
   }

    @Override
    public WF17Exception createErrorResponse(Throwable throwable) {
         return new WF17InvalidInputException(HttpStatus.BAD_REQUEST,
            new WF17ErrorDetails(WF17ErrorCodes.UNKNOWN_ERROR,"Testing error"));
    }
}
