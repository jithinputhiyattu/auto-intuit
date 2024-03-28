package com.dreamblitz.webflux17.service;

import com.dreamblitz.webflux17.common.exception.WF17ErrorCodes;
import com.dreamblitz.webflux17.common.exception.WF17ErrorDetails;
import com.dreamblitz.webflux17.common.exception.WF17Exception;
import reactor.core.publisher.Mono;

public abstract class IService {

    private boolean isHandled(Throwable throwable) {
        if(throwable instanceof WF17Exception){
            return true;
        }
        return false;
    }

    public Mono<String> errorHandler(Throwable throwable) {
        if(isHandled(throwable)) {
            WF17Exception oldException =  (WF17Exception)(throwable);
            WF17Exception newException = createErrorResponse(throwable);
            newException.addError(oldException.getErrors());
            return Mono.error(newException);
        }
        return Mono.error(createErrorResponse(throwable));
    }

    abstract WF17Exception createErrorResponse(Throwable throwable);

}
