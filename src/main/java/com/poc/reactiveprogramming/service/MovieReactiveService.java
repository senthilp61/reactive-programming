package com.poc.reactiveprogramming.service;

import com.poc.reactiveprogramming.domain.Movie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieReactiveService {

    Flux<Movie> getAllMovies();

    Mono<Movie> getMovieById(long movieId);

    Mono<Movie> getMovieByIdUsingFlatMap(long movieId);
}
