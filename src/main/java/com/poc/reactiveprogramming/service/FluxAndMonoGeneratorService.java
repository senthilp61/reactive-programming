package com.poc.reactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FluxAndMonoGeneratorService {

    Flux<String> namesFlux();
    Flux<String> namesFluxMap();
    Flux<String> namesFluxImmutable();
    Flux<String> namesFluxMap(int stringLenght);
    Flux<String> namesFluxFlatMap(int stringLenght);
    Flux<String> namesFluxFlatMapAsync(int stringLenght);
    Flux<String> namesFluxConcatMap(int stringLenght);
    Flux<String> namesFluxTransform(int stringLenght);
    Flux<String> namesFluxTransformSwitchIfEmpty(int stringLenght);
    Flux<String> exploreConcat();
    Flux<String> exploreConcatWith();
    Flux<String> exploreConcatWithMono();
    Flux<String> exploreMerge();
    Flux<String> exploreMergeWith();
    Flux<String> exploreMergeSequential();
    Flux<String> exploreMergeWithMono();
    Flux<String> exploreZip();
    Flux<String> exploreZipWith();
    Mono<String> exploreZipWithMono();
    Flux<String> exploreZip1();
    Flux<String> splitString(String name);
    Flux<String> splitStringWithDelay(String name);
    Mono<String> nameMono();
    Mono<String> nameMonoMap(int stringLength);
    Mono<List<String>> nameMonoFlatMap(int stringLength);
    Flux<String> nameMonoFlatMapMany(int stringLength);
    Mono<List<String>> nameMonoFlatMapTransform(int stringLength);
    Mono<List<String>> nameMonoFlatMapDefaultIfEmpty(int stringLength);
    Mono<List<String>> nameMonoFlatMapSwitchIfEmpty(int stringLength);
    Mono<List<String>> splitStringMono(String s);
    Flux<String> exceptionFlux();
    Flux<String> exploreOnErrorReturn();
    Flux<String> exploreOnErrorResume(Exception e);
    Flux<String> exploreOnErrorContinue();
    Flux<String> exploreOnErrorMap();
    Flux<String> exploreDoOnError();
    Mono<Object> exploreMonoOnErrorReturn();
}
