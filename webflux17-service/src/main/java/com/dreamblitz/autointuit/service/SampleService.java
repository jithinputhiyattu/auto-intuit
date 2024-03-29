package com.dreamblitz.autointuit.service;

import com.dreamblitz.autointuit.common.exception.*;
import com.dreamblitz.autointuit.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SampleService extends IService {

    public Mono<String> sampleResponse() throws AutoIntuitUnhandledException {
     try {
        String x = null;
        if(x.toString()=="y") {
            /// DO nothing
        }
        return errorHandler(new Throwable("error 0"));
       // return  Mono.just("Jithin ....");

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
