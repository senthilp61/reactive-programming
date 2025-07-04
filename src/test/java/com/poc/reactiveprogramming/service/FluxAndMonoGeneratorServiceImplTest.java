package com.poc.reactiveprogramming.service;

import com.poc.reactiveprogramming.exception.ReactorException;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

class FluxAndMonoGeneratorServiceImplTest {

    FluxAndMonoGeneratorServiceImpl fluxAndMonoGeneratorService =
            new FluxAndMonoGeneratorServiceImpl();

    @Test
    void namesFlux() {
        //Given

        //When
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        //Then
        StepVerifier.create(namesFlux)
                //.expectNext("alex", "ben", "chloe")
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void nameMono() {
        //Given

        //When
        var nameMono = fluxAndMonoGeneratorService.nameMono();

        //Then
        StepVerifier.create(nameMono)
                .expectNext("alex")
                .verifyComplete();
    }

    @Test
    void namesFluxMap() {
        //Given

        //When
        var namesFlux = fluxAndMonoGeneratorService.namesFluxMap();

        //Then
        StepVerifier.create(namesFlux)
                .expectNext("ALEX", "BEN", "CHLOE")
                .verifyComplete();
    }

    @Test
    void namesFluxImmutable() {
        //Given

        //When
        var namesFlux = fluxAndMonoGeneratorService.namesFluxImmutable();

        //Then
        StepVerifier.create(namesFlux)
                .expectNext("ALEX", "BEN", "CHLOE");
    }

    @Test
    void testNamesFluxMap() {
        //Given
        int stringLength = 3;

        //When
        var namesFlux = fluxAndMonoGeneratorService.namesFluxMap(stringLength);

        //Then
        StepVerifier.create(namesFlux)
                .expectNext("4-ALEX", "5-CHLOE")
                .verifyComplete();
    }

    @Test
    void nameMonoMap() {
        //Given
        int stringLength = 3;

        //When
        var nameMono = fluxAndMonoGeneratorService.nameMonoMap(stringLength);

        //Then
        StepVerifier.create(nameMono)
                .expectNext("4-ALEX")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMap() {
        //Given
        int stringLength = 3;

        //When
        var charFlux = fluxAndMonoGeneratorService.namesFluxFlatMap(stringLength);

        //Then
        StepVerifier.create(charFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMapAsync() {
        //Given
        int stringLength = 3;

        //When
        var charFlux = fluxAndMonoGeneratorService.namesFluxFlatMapAsync(stringLength);

        //Then
        StepVerifier.create(charFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void namesFluxConcatMap() {
        //Given
        int stringLength = 3;

        //When
        var charFlux = fluxAndMonoGeneratorService.namesFluxConcatMap(stringLength);

        //Then
        StepVerifier.create(charFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMap() {
        //Given
        int stringLength = 3;

        //When
        var value = fluxAndMonoGeneratorService.nameMonoFlatMap(stringLength);

        //Then
        StepVerifier.create(value)
                .expectNext(List.of("A", "L", "E", "X"))
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMapMany() {
        //Given
        int stringLength = 3;

        //When
        var value = fluxAndMonoGeneratorService.nameMonoFlatMapMany(stringLength);

        //Then
        StepVerifier.create(value)
                .expectNext("A", "L", "E", "X")
                .verifyComplete();
    }

    @Test
    void namesFluxTransform() {
        //Given
        int stringLength = 3;

        //When
        var value = fluxAndMonoGeneratorService.namesFluxTransform(stringLength);

        //Then
        StepVerifier.create(value)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void testNamesFluxTransform() {
        //Given
        int stringLength = 6;

        //When
        var value = fluxAndMonoGeneratorService.namesFluxTransform(stringLength);

        //Then
        StepVerifier.create(value)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void namesFluxTransformSwitchIfEmpty() {
        //Given
        int stringLength = 6;

        //When
        var value = fluxAndMonoGeneratorService.namesFluxTransformSwitchIfEmpty(stringLength);

        //Then
        StepVerifier.create(value)
                .expectNext("D", "E", "F", "A", "U", "L", "T")
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMapTransform() {
        //Given
        int stringLength = 3;

        //When
        var value = fluxAndMonoGeneratorService.nameMonoFlatMapTransform(stringLength);

        //Then
        StepVerifier.create(value)
                .expectNext(List.of("A", "L", "E", "X"))
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMapDefaultIfEmpty() {
        //Given
        int stringLength = 6;

        //When
        var value = fluxAndMonoGeneratorService.nameMonoFlatMapDefaultIfEmpty(stringLength);

        //Then
        StepVerifier.create(value)
                .expectNext(List.of("D", "E", "F", "A", "U", "L", "T"))
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMapSwitchIfEmpty() {
        //Given
        int stringLength = 6;

        //When
        var value = fluxAndMonoGeneratorService.nameMonoFlatMapSwitchIfEmpty(stringLength);

        //Then
        StepVerifier.create(value)
                .expectNext(List.of("D", "E", "F", "A", "U", "L", "T"))
                .verifyComplete();
    }

    @Test
    void exploreConcat() {
        //Given

        //When
        var concatFlux = fluxAndMonoGeneratorService.exploreConcat();

        //Then
        StepVerifier.create(concatFlux)
                .expectNext("a", "b", "c", "d", "e", "f")
                .verifyComplete();
    }

    @Test
    void exploreConcatWith() {
        //Given

        //When
        var concatFlux = fluxAndMonoGeneratorService.exploreConcatWith();

        //Then
        StepVerifier.create(concatFlux)
                .expectNext("a", "b", "c", "d", "e", "f")
                .verifyComplete();
    }

    @Test
    void exploreConcatWithMono() {
        //Given

        //When
        var concatFlux = fluxAndMonoGeneratorService.exploreConcatWithMono();

        //Then
        StepVerifier.create(concatFlux)
                .expectNext("a","d", "e", "f")
                .verifyComplete();
    }

    @Test
    void exploreMerge() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreMerge();

        //Then
        StepVerifier.create(value)
                .expectNext("a",  "d", "b",  "e", "c", "f")
                .verifyComplete();
    }

    @Test
    void exploreMergeWith() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreMergeWith();

        //Then
        StepVerifier.create(value)
                .expectNext("a",  "d", "b",  "e", "c", "f")
                .verifyComplete();
    }

    @Test
    void exploreMergeWithMono() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreMergeWithMono();

        //Then
        StepVerifier.create(value)
                .expectNext("a", "d")
                .verifyComplete();
    }

    @Test
    void exploreMergeSequential() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreMergeSequential();

        //Then
        StepVerifier.create(value)
                .expectNext("a","b", "c",   "d",  "e","f")
                .verifyComplete();
    }

    @Test
    void exploreZip1() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreZip1();

        //Then
        StepVerifier.create(value)
                .expectNext("ADGJ", "BEHK", "CFIL")
                .verifyComplete();
    }

    @Test
    void exploreZip() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreZip();

        //Then
        StepVerifier.create(value)
                .expectNext("AD", "BE", "CF")
                .verifyComplete();
    }

    @Test
    void exploreZipWith() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreZipWith();

        //Then
        StepVerifier.create(value)
                .expectNext("AD", "BE", "CF")
                .verifyComplete();
    }

    @Test
    void exploreZipWithMono() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreZipWithMono();

        //Then
        StepVerifier.create(value)
                .expectNext("AD")
                .verifyComplete();
    }

    @Test
    void exceptionFlux() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exceptionFlux();

        //Then
        StepVerifier.create(value)
                .expectNext("A", "B", "C")
                //.expectError(RuntimeException.class)
                .expectErrorMessage("Exception Occured")
                .verify();
    }

    @Test
    void exploreOnErrorReturn() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreOnErrorReturn();

        //Then
        StepVerifier.create(value)
                .expectNext("A", "B", "C", "D")
                .verifyComplete();
    }

    @Test
    void exploreOnErrorResume() {
        //Given
        var e = new IllegalStateException("Not a valid State");

        //When
        var value = fluxAndMonoGeneratorService.exploreOnErrorResume(e);

        //Then
        StepVerifier.create(value)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();
    }

    @Test
    void testExploreOnErrorResume() {
        //Given
        var e = new RuntimeException("Not a valid State");

        //When
        var value = fluxAndMonoGeneratorService.exploreOnErrorResume(e);

        //Then
        StepVerifier.create(value)
                .expectNext("A", "B", "C")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void exploreOnErrorContinue() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreOnErrorContinue();

        //Then
        StepVerifier.create(value)
                .expectNext("A", "C", "D")
                .verifyComplete();
    }

    @Test
    void exploreOnErrorMap() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreOnErrorMap();

        //Then
        StepVerifier.create(value)
                .expectNext("A")
                .expectError(ReactorException.class)
                .verify();
    }

    @Test
    void exploreDoOnError() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreDoOnError();

        //Then
        StepVerifier.create(value)
                .expectNext("A", "B", "C")
                .expectError(IllegalStateException.class)
                .verify();
    }

    @Test
    void exploreMonoOnErrorReturn() {
        //Given

        //When
        var value = fluxAndMonoGeneratorService.exploreMonoOnErrorReturn();

        //Then
        StepVerifier.create(value)
                .expectNext("abc")
                .verifyComplete();
    }
}