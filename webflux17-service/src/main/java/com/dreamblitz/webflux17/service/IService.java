package com.dreamblitz.webflux17.service;

import com.dreamblitz.autointuit.common.exception.AutoIntuitException;
import reactor.core.publisher.Mono;

public abstract class IService {

    private boolean isHandled(Throwable throwable) {
        if(throwable instanceof AutoIntuitException){
            return true;
        }
        return false;
    }

    public Mono<String> errorHandler(Throwable throwable) {
        if(isHandled(throwable)) {
            AutoIntuitException oldException =  (AutoIntuitException)(throwable);
            AutoIntuitException newException = createErrorResponse(throwable);
            newException.addError(oldException.getErrors());
            return Mono.error(newException);
        }
        return Mono.error(createErrorResponse(throwable));
    }

    abstract AutoIntuitException createErrorResponse(Throwable throwable);

}
