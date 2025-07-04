package com.poc.reactiveprogramming.service;

import com.poc.reactiveprogramming.domain.MovieInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MovieInfoService {

    Flux<MovieInfo> retrieveMoviesFlux();
    Mono<MovieInfo> retrieveMovieInfoMonoUsingId(long movieId);
    List<MovieInfo> movieList();
}
