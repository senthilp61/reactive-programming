package com.poc.reactiveprogramming.service;

import com.poc.reactiveprogramming.domain.Movie;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MovieReactiveServiceImplTest {

    private MovieInfoService movieInfoService = new MovieInfoServiceImpl();
    private ReviewService reviewService = new ReviewServiceImpl();

    private MovieReactiveService movieReactiveService
            = new MovieReactiveServiceImpl(movieInfoService, reviewService);

    @Test
    void getAllMovies() {
        //Given

        //When
        var moviesFlux = movieReactiveService.getAllMovies();

        //Then
        StepVerifier.create(moviesFlux)
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovieInfo().getName());
                    assertEquals(2, movie.getReviewList().size());
                })
                .assertNext(movie -> {
                    assertEquals("The Dark Knight", movie.getMovieInfo().getName());
                    assertEquals(2, movie.getReviewList().size());
                })
                .assertNext(movie -> {
                    assertEquals("Dark Knight Rises", movie.getMovieInfo().getName());
                    assertEquals(2, movie.getReviewList().size());
                })
                .verifyComplete();
    }

    @Test
    void getMovieById() {
        //Given
        long movieId = 100l;

        //When
        Mono<Movie> movieMono = movieReactiveService.getMovieById(movieId);

        //Then
        StepVerifier.create(movieMono)
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovieInfo().getName());
                    assertEquals(2, movie.getReviewList().size());
                    //name of the movie
                    //reviewList
                })
                .verifyComplete();
    }

    @Test
    void getMovieByIdUsingFlatMap() {
        //Given
        long movieId = 100l;

        //When
        Mono<Movie> movieMono = movieReactiveService.getMovieByIdUsingFlatMap(movieId);

        //Then
        StepVerifier.create(movieMono)
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovieInfo().getName());
                    assertEquals(2, movie.getReviewList().size());
                })
                .verifyComplete();
    }
}