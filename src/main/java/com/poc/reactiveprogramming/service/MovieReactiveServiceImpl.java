package com.poc.reactiveprogramming.service;

import com.poc.reactiveprogramming.domain.Movie;
import com.poc.reactiveprogramming.domain.MovieInfo;
import com.poc.reactiveprogramming.domain.Review;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieReactiveServiceImpl implements MovieReactiveService{

    private final MovieInfoService movieInfoService;
    private final ReviewService reviewService;

    @Override
    public Flux<Movie> getAllMovies() {

        var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();

        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retireveReviewsFlux(movieInfo.getMovieInfoId())
                            .collectList();
                    return reviewsMono
                            .map(reviewList -> new Movie(movieInfo, reviewList, null));
                })
                .log();
    }

    @Override
    public Mono<Movie> getMovieById(long movieId) {

        Mono<MovieInfo> movieInfoMono = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        Mono<List<Review>> reviewFlux = reviewService.retireveReviewsFlux(movieId).collectList();
        return movieInfoMono
                .zipWith(reviewFlux, (movieInfo, reviews) -> new Movie(movieInfo, reviews, null))
                .log();

    }

    @Override
    public Mono<Movie> getMovieByIdUsingFlatMap(long movieId) {
        Mono<MovieInfo> movieInfoMono = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        return movieInfoMono
                .flatMap(movieInfo -> {
                            Mono<List<Review>> reviewFlux = reviewService.retireveReviewsFlux(movieId).collectList();
                            return reviewFlux
                                    .map(reviews -> new Movie(movieInfo, reviews, null));
                })
                .log();
    }


}
