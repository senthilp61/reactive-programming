package com.poc.reactiveprogramming.service;

import com.poc.reactiveprogramming.domain.MovieInfo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieInfoServiceImpl implements MovieInfoService{

    public Flux<MovieInfo> retrieveMoviesFlux() {
        List<MovieInfo> movieInfoList = List.of(new MovieInfo(100l, "Batman Begins", 2005, java.util.List.of("Chritian Bale"), LocalDate.of(2005, 02, 13)),
                                                new MovieInfo(101l, "The Dark Knight", 2008, List.of("Chritian Bale", "Heath Ledger"), LocalDate.of(2005, 02, 14)),
                                                new MovieInfo(102l, "Dark Knight Rises", 2008, List.of("Chritian Bale", "Tom Hardy"), LocalDate.of(2005, 02, 15)));
        return Flux.fromIterable(movieInfoList);
    }

    public Mono<MovieInfo> retrieveMovieInfoMonoUsingId(long movieId) {

        var movie = new MovieInfo(movieId, "Batman Begins", 2005, List.of("Chritian Bale", "Michael Cane"), LocalDate.of(2005, 02, 13));
        return Mono.just(movie);
    }

    public List<MovieInfo> movieList() {

        delay(1000);

        return List.of(new MovieInfo(100l, "Batman Begins", 2005, java.util.List.of("Chritian Bale"), LocalDate.of(2005, 02, 13)),
                new MovieInfo(101l, "The Dark Knight", 2008, List.of("Chritian Bale", "Heath Ledger"), LocalDate.of(2005, 02, 14)),
                new MovieInfo(102l, "Dark Knight Rises", 2008, List.of("Chritian Bale", "Tom Hardy"), LocalDate.of(2005, 02, 15)));

    }

    private void delay(int i) {

        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
