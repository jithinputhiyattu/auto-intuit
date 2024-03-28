package com.dreamblitz.autointuit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.dreamblitz.webflux17.*" })
@ComponentScan(basePackages={"com.dreamblitz.autointuit.*" })
//@ComponentScan({"com.dreamblitz.webflux17.adapter.controller", "com.dreamblitz.webflux17.adapter.controller.*" })
public class WebFlux17ApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(WebFlux17ApplicationMain.class, args);
	}

}
