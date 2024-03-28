package com.dreamblitz.autointuit.adapter.controller;


import com.dreamblitz.autointuit.common.exception.AutoIntuitUnhandledException;
import com.dreamblitz.webflux17.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/auto-intuit-1")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public Mono<ResponseEntity<String>> getDistributionsStatus() throws AutoIntuitUnhandledException {
       return sampleService.sampleResponse().map( text -> new ResponseEntity<>(text + " Jai", HttpStatus.OK));
    }

    @RequestMapping(value = "/sample2", method = RequestMethod.GET)
    public Mono<ResponseEntity<String>> getDistributionsStatus2() throws AutoIntuitUnhandledException {
        return sampleService.sampleResponse().map( text -> new ResponseEntity<>(text + " Jai", HttpStatus.OK));
    }

}
