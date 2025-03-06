package com.poc.reactive_programming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ReactiveProgrammingApplication {

	public static void main(String[] args) {

		SpringApplication.run(ReactiveProgrammingApplication.class, args);

		Flux<String> fruitFlux = Flux.just("Apple", "Banana", "Mango", "Orange");

		fruitFlux
				.map(String::toUpperCase)
				.subscribe(fruit -> System.out.println("Fruit : "+fruit));

		Mono<String> singleFruit = Mono.just("Strawberry");

		singleFruit
				.map(String::toUpperCase)
				.subscribe(fruit -> System.out.println("Single Fruit: "+fruit));
	}

}
