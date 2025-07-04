package com.poc.reactiveprogramming.service;

import com.poc.reactiveprogramming.exception.ReactorException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

@Slf4j
public class FluxAndMonoGeneratorServiceImpl implements FluxAndMonoGeneratorService{

    public Flux<String> namesFlux() {

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .log();  // db or a remote service call
    }

    public Flux<String> namesFluxMap() {

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                //.map(s -> s.toUpperCase())
                .log();  // db or a remote service call
    }

    public Flux<String> namesFluxImmutable() {

        var namesFlux = Flux.fromIterable(List.of("alex", "ben", "chloe"));
        namesFlux.map(String::toUpperCase);
        return namesFlux;
    }

    public Flux<String> namesFluxMap(int stringLenght) {

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                //.map(s -> s.toUpperCase())
                .filter(s -> s.length() > 3)
                .map(s -> s.length()+"-"+s)
                .doOnNext(name -> {
                    System.out.println("Name is : " + name);
                    System.out.println("Name is : " + name.toLowerCase());
                })
                .doOnSubscribe(s -> System.out.println("Subscription is : " + s))
                .doOnComplete(() -> System.out.println("Inside the complete callback"))
                .doFinally(signalType -> System.out.println("Inside doFinally : " + signalType))
                .log();  // db or a remote service call

        // Pipeline in Functional Programming
    }

    public Flux<String> namesFluxFlatMap(int stringLenght) {

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                //.map(s -> s.toUpperCase())
                .filter(s -> s.length() > 3)
                // ALEX, CHLOE -> A, L, E, X, C, H, L, O, E
                .flatMap(s -> splitString(s))       // A, L, E, X, C, H, L, O, E
                //.map(s -> s.length()+"-"+s)
                .log();  // db or a remote service call

        // Pipeline in Functional Programming
    }

    public Flux<String> namesFluxFlatMapAsync(int stringLenght) {

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                //.map(s -> s.toUpperCase())
                .filter(s -> s.length() > 3)
                // ALEX, CHLOE -> A, L, E, X, C, H, L, O, E
                //.flatMap(s -> splitStringWithDelay(s))       // A, L, E, X, C, H, L, O, E
                .concatMap(s -> splitStringWithDelay(s))
                //.map(s -> s.length()+"-"+s)
                .log();  // db or a remote service call

        // Pipeline in Functional Programming
    }

    public Flux<String> namesFluxConcatMap(int stringLenght) {

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                //.map(s -> s.toUpperCase())
                .filter(s -> s.length() > 3)
                // ALEX, CHLOE -> A, L, E, X, C, H, L, O, E
                .concatMap(s -> splitStringWithDelay(s))       // A, L, E, X, C, H, L, O, E
                //.map(s -> s.length()+"-"+s)
                .log();  // db or a remote service call

        // Pipeline in Functional Programming
    }

    public Flux<String> namesFluxTransform(int stringLenght) {

        Function<Flux<String>, Flux<String>> filterMap = name -> name.map(String::toUpperCase)
                .filter(s -> s.length() > stringLenght);

        //Flux.empty();
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(filterMap)
                .flatMap(s -> splitString(s))       // A, L, E, X, C, H, L, O, E
                .defaultIfEmpty("default")
                .log();  // db or a remote service call

        // Pipeline in Functional Programming
    }

    public Flux<String> namesFluxTransformSwitchIfEmpty(int stringLenght) {

        Function<Flux<String>, Flux<String>> filterMap = name -> name.map(String::toUpperCase)
                .filter(s -> s.length() > stringLenght)
                .flatMap(s -> splitString(s));       // A, L, E, X, C, H, L, O, E

        Flux<String> defaultFlux = Flux.just("default")
                .transform(filterMap);      // D, E, F, A, U, L, T
        //Flux.empty();
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(filterMap)
                .switchIfEmpty(defaultFlux)
                .log();  // db or a remote service call

        // Pipeline in Functional Programming
    }

    public Flux<String> exploreConcat() {

        var abcFlux = Flux.just("a", "b", "c");
        var defFlux = Flux.just("d", "e", "f");

        return Flux.concat(abcFlux, defFlux)
                .log();
    }

    public Flux<String> exploreConcatWith() {

        var abcFlux = Flux.just("a", "b", "c");
        var defFlux = Flux.just("d", "e", "f");

        return abcFlux.concatWith(defFlux)
                .log();
    }

    public Flux<String> exploreConcatWithMono() {

        var abcMono = Mono.just("a");
        var defFlux = Flux.just("d", "e", "f");

        return abcMono.concatWith(defFlux)
                .log();
    }

    public Flux<String> exploreMerge() {

        var abcFlux = Flux.just("a", "b", "c")
                .delayElements(Duration.ofMillis(100));
        var defFlux = Flux.just("d", "e", "f")
                .delayElements(Duration.ofMillis(125));

        return Flux.merge(abcFlux, defFlux)
                .log();
    }

    public Flux<String> exploreMergeWith() {

        var abcFlux = Flux.just("a", "b", "c")
                .delayElements(Duration.ofMillis(100));
        var defFlux = Flux.just("d", "e", "f")
                .delayElements(Duration.ofMillis(125));

        return abcFlux.mergeWith(defFlux)
                .log();
    }

    public Flux<String> exploreMergeSequential() {

        var abcFlux = Flux.just("a", "b", "c")
                .delayElements(Duration.ofMillis(100));
        var defFlux = Flux.just("d", "e", "f")
                .delayElements(Duration.ofMillis(125));

        return Flux.mergeSequential(abcFlux, defFlux)
                .log();
    }

    public Flux<String> exploreMergeWithMono() {

        var aMono = Mono.just("a");
        var bMono = Flux.just("d");

        return aMono.mergeWith(bMono)
                .log();
    }

    public Flux<String> exploreZip() {

        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return Flux.zip(abcFlux, defFlux, (x, y) -> x+y).log();
    }

    public Flux<String> exploreZipWith() {

        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return abcFlux.zipWith(defFlux, (x, y) -> x+y).log();
    }

    public Mono<String> exploreZipWithMono() {

        var aMono = Mono.just("A");
        var dMono = Mono.just("D");

        return aMono.zipWith(dMono)
                .map(t2 -> t2.getT1()+t2.getT2())
                .log();
    }

    public Flux<String> exploreZip1() {

        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");
        var gFlux = Flux.just("G", "H", "I");
        var jFlux = Flux.just("J", "K", "L");

        return Flux.zip(abcFlux, defFlux, gFlux, jFlux)
                .map(t4 -> t4.getT1()+t4.getT2()+t4.getT3()+t4.getT4())
                .log();
    }

    // ALEX -> A, L, E, X
    public Flux<String> splitString(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    public Flux<String> splitStringWithDelay(String name) {
        var charArray = name.split("");
        var randomInt = new Random().nextInt(1000);
        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(randomInt));
    }

    public Mono<String> nameMono() {

        return Mono.just("alex")
                .log();
    }

    public Mono<String> nameMonoMap(int stringLength) {

        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .map(s -> s.length()+"-"+s)
                .log();
    }

    public Mono<List<String>> nameMonoFlatMap(int stringLength) {

        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringMono)
                .log();
    }

    public Flux<String> nameMonoFlatMapMany(int stringLength) {

        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMapMany(this::splitString)
                .log();
    }


    public Mono<List<String>> nameMonoFlatMapTransform(int stringLength) {

        Function<Mono<String>, Mono<List<String>> > filterMap = name -> name.map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringMono);

        //Mono.empty()
        return Mono.just("alex")
                .transform(filterMap)
                .log();
    }

    public Mono<List<String>> nameMonoFlatMapDefaultIfEmpty(int stringLength) {

        Function<Mono<String>, Mono<List<String>> > filterMap = name -> name.map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringMono);

        //Mono.empty()
        return Mono.just("alex")
                .transform(filterMap)
                .defaultIfEmpty(List.of("D", "E", "F", "A", "U", "L", "T"))
                .log();
    }

    public Mono<List<String>> nameMonoFlatMapSwitchIfEmpty(int stringLength) {

        Function<Mono<String>, Mono<List<String>> > filterMap = name -> name.map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringMono);

        Mono<List<String>> defaultFlux = Mono.just("default")
                .transform(filterMap);


        //Mono.empty()
        return Mono.just("alex")
                .transform(filterMap)
                .switchIfEmpty(defaultFlux)
                .log();
    }

    public Mono<List<String>> splitStringMono(String s) {
        var charArray = s.split("");
        var charList = List.of(charArray); // ALEX -> List<A, L, E, X>
        return Mono.just(charList);

    }

    @Override
    public Flux<String> exceptionFlux() {

        return Flux.just("A", "B", "C")
                .concatWith(Flux.error(new RuntimeException("Exception Occured")))
                .concatWith(Flux.just("D"))
                .log();
    }

    @Override
    public Flux<String> exploreOnErrorReturn() {

        return Flux.just("A", "B", "C")
                .concatWith(Flux.error(new IllegalStateException("Exception Occured")))
                .onErrorReturn("D")
                .log();

    }

    @Override
    public Flux<String> exploreOnErrorResume(Exception e) {

        var recoverFlux = Flux.just("D", "E", "F");

        return Flux.just("A", "B", "C")
                .concatWith(Flux.error(e))
                .onErrorResume(ex -> {
                    log.error("Exception is : {}", ex.getMessage(), ex);
                    if(ex instanceof IllegalStateException)
                        return recoverFlux;
                    else
                        return Flux.error(ex);
                })
                .log();
    }

    @Override
    public Flux<String> exploreOnErrorContinue() {

        return Flux.just("A", "B", "C")
                .map(name -> {
                        if(name.equalsIgnoreCase("B"))
                            throw new IllegalStateException("Exception Occured");
                        return name;
                })
                .concatWith(Flux.just("D"))
                .onErrorContinue((ex, name) -> {
                    log.error("Exception is : {} ", ex.getMessage(), ex);
                    log.info("name is {}", name);
                })
                .log();
    }

    @Override
    public Flux<String> exploreOnErrorMap() {

        return Flux.just("A", "B", "C")
                .map(name -> {
                    if(name.equalsIgnoreCase("B"))
                        throw new IllegalStateException("Exception Occured");
                    return name;
                })
                .concatWith(Flux.just("D"))
                .onErrorMap(ex -> {
                    log.error("Exception is - {}", ex.getMessage(), ex);
                    return new ReactorException(ex, ex.getMessage());
                })
                .log();
    }

    @Override
    public Flux<String> exploreDoOnError() {

        return Flux.just("A", "B", "C")
                .concatWith(Flux.error(new IllegalStateException("Exception Occured")))
                .doOnError(ex -> log.error("Exception is - {}",ex.getMessage(), ex))
                .log();
    }

    @Override
    public Mono<Object> exploreMonoOnErrorReturn() {

        return Mono.just("A")
                .map(val -> {
                    throw new RuntimeException("Exception Occured");
                })
                .onErrorReturn("abc")
                .log();
    }

    public static void main(String[] args) {

        FluxAndMonoGeneratorServiceImpl fluxAndMonoGeneratorService =
                new FluxAndMonoGeneratorServiceImpl();
        fluxAndMonoGeneratorService.namesFlux()
                .subscribe(name -> System.out.println("Name is : " + name));

        fluxAndMonoGeneratorService.nameMono()
                .subscribe(name -> System.out.println("Mono Name is : " + name));
    }
}
